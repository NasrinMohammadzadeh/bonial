package com.example.bonialchallenge

import com.example.bonialchallenge.presentation.feature.repository.ProductListRepositoryImpl
import com.example.bonialchallenge.presentation.feature.ui.model.ProductListModel
import com.example.bonialchallenge.presentation.feature.usecase.ProductListUseCase
import com.example.bonialchallenge.presentation.feature.viewmodel.ProductListViewModel
import com.example.bonialchallenge.model.ProductListResponse
import com.example.bonialchallenge.model.mapToModel
import com.example.bonialchallenge.network.LoadingListIntent
import com.example.bonialchallenge.network.MVVMException
import com.example.bonialchallenge.network.MVVMResult
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    private val testDispatcher: CoroutineDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var productListRepository: ProductListRepositoryImpl

    private lateinit var closeable: AutoCloseable

    private lateinit var viewModel: ProductListViewModel

    private lateinit var getProductListUseCase: ProductListUseCase

    private val mockProductList: MVVMResult<ProductListModel?> =
        MVVMResult.Success(readJsonFile<ProductListResponse>("src/test/resources/product_list.json").mapToModel())

    private val mockError: MVVMResult<ProductListModel?> = MVVMResult.Error(
        MVVMException(
            LoadingListIntent,
            Throwable(message = "something is wrong")
        )
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        closeable = MockitoAnnotations.openMocks(this)

        getProductListUseCase = ProductListUseCase(
            productListRepository = productListRepository,
            ioDispatcher = testDispatcher
        )
    }

    private fun setViewModel() {
        viewModel = ProductListViewModel(
            productListUseCase = getProductListUseCase
        )
    }

    private suspend fun arrangeResponse() {
        whenever(productListRepository.getProductsList()).thenReturn(mockProductList)
    }

    private suspend fun arrangeErrorResponse() {
        whenever(productListRepository.getProductsList()).thenReturn(mockError)
    }

    @Test
    fun `test initScreen initializes data correctly`() = runTest(testDispatcher) {
        arrangeResponse()

        advanceUntilIdle()
        setViewModel()

        advanceUntilIdle()
        viewModel.call()
        advanceUntilIdle()

        assertNotNull(viewModel.uiState.value.contents)
        assertEquals(null, viewModel.uiState.value.error)

    }

    @Test
    fun `test initScreen initializes data with error`() = runTest(testDispatcher) {
        arrangeErrorResponse()

        advanceUntilIdle()
        setViewModel()

        advanceUntilIdle()
        viewModel.call()
        advanceUntilIdle()

        assertNull(viewModel.uiState.value.contents)
        TestCase.assertEquals(
            "something is wrong",
            viewModel.uiState.value.error?.baseError?.throwable?.message
        )

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        closeable.close()
    }
}
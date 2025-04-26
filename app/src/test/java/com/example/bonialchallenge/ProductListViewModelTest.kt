package com.example.bonialchallenge

import com.example.bonialchallenge.feature.repository.ProductListRepositoryImpl
import com.example.bonialchallenge.feature.ui.model.ProductListModel
import com.example.bonialchallenge.feature.usecase.ProductListUseCase
import com.example.bonialchallenge.feature.viewmodel.ProductListViewModel
import com.example.bonialchallenge.model.ProductListResponse
import com.example.bonialchallenge.model.mapToModel
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

    private val mockProductList: ProductListModel = readJsonFile<ProductListResponse>("src/test/resources/product_list.json").mapToModel()

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

    @Test
    fun `test initScreen initializes data correctly`() = runTest(testDispatcher) {
        arrangeResponse()

        advanceUntilIdle()
        setViewModel()

        advanceUntilIdle()

        assertNotNull(viewModel.uiState.value)
        assertEquals(null, viewModel.error.value)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        closeable.close()
    }
}
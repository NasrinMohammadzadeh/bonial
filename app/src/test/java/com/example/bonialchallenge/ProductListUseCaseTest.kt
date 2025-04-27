package com.example.bonialchallenge

import com.example.bonialchallenge.presentation.feature.repository.ProductListRepository
import com.example.bonialchallenge.presentation.feature.ui.model.ProductListModel
import com.example.bonialchallenge.presentation.feature.usecase.ProductListUseCase
import com.example.bonialchallenge.model.ProductListResponse
import com.example.bonialchallenge.model.mapToModel
import com.example.bonialchallenge.network.LoadingListIntent
import com.example.bonialchallenge.network.MVVMException
import com.example.bonialchallenge.network.MVVMResult
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class ProductListUseCaseTest {

    @Mock
    private lateinit var productListRepository: ProductListRepository

    private lateinit var getProductListRepository: ProductListUseCase

    private val testDispatcher: CoroutineDispatcher = StandardTestDispatcher()

    private lateinit var closeable: AutoCloseable

    private val mockProductList: MVVMResult<ProductListModel?> = MVVMResult.Success(readJsonFile<ProductListResponse>("src/test/resources/product_list.json").mapToModel())

    private val mockError: MVVMResult<ProductListModel?> = MVVMResult.Error(MVVMException(LoadingListIntent,
        Throwable(message = "something is wrong")
    ))

    @Before
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)

        getProductListRepository = ProductListUseCase(
            productListRepository = productListRepository,
            ioDispatcher = testDispatcher
        )
    }

    private suspend fun arrangeSuccessResponse() {
        whenever(productListRepository.getProductsList()).thenReturn(mockProductList)
    }

    private suspend fun arrangeErrorResponse() {
        whenever(productListRepository.getProductsList()).thenReturn(mockError)
    }

    @Test
    fun `test invoke product list case`() = runTest(testDispatcher) {
        arrangeSuccessResponse()
        val result = getProductListRepository.invoke()
        assertNotNull(result)
    }

    @Test
    fun `test invoke product list with error case`() = runTest(testDispatcher) {
        arrangeErrorResponse()
        val result = getProductListRepository.invoke()

       assertEquals("something is wrong", (result.productList as MVVMResult.Error).baseError.throwable.message)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        closeable.close()
    }
}
package com.example.bonialchallenge

import com.example.bonialchallenge.feature.repository.ProductListRepository
import com.example.bonialchallenge.feature.ui.model.ProductListModel
import com.example.bonialchallenge.feature.usecase.ProductListUseCase
import com.example.bonialchallenge.model.ProductListResponse
import com.example.bonialchallenge.model.mapToModel
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

    private val mockProductList: ProductListModel = readJsonFile<ProductListResponse>("src/test/resources/product_list.json").mapToModel()

    @Before
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)

        getProductListRepository = ProductListUseCase(
            productListRepository = productListRepository,
            ioDispatcher = testDispatcher
        )
    }

    private suspend fun arrangeResponse() {
        whenever(productListRepository.getProductsList()).thenReturn(mockProductList)
    }

    @Test
    fun `test invoke product list case`() = runTest(testDispatcher) {
        arrangeResponse()
        val result = getProductListRepository.invoke()
        assertNotNull(result)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        closeable.close()
    }
}
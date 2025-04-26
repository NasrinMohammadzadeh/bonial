package com.example.bonialchallenge.feature.usecase

import com.example.bonialchallenge.di.IoDispatcher
import com.example.bonialchallenge.feature.repository.ProductListRepository
import com.example.bonialchallenge.feature.ui.model.ProductDataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductListUseCase @Inject constructor(
    private val productListRepository: ProductListRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): ProductDataState = withContext(ioDispatcher) {
        val productList = async { productListRepository.getProductsList() }


        ProductDataState(
            productList = productList.await()
        )
    }
}
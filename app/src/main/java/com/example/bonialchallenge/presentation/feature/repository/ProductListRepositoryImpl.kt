package com.example.bonialchallenge.presentation.feature.repository

import com.example.bonialchallenge.presentation.feature.ui.model.ProductListModel
import com.example.bonialchallenge.model.mapToModel
import com.example.bonialchallenge.network.LoadingListIntent
import com.example.bonialchallenge.network.MVVMException
import com.example.bonialchallenge.network.MVVMResult
import com.example.bonialchallenge.network.ProductListApi
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(private val api: ProductListApi): ProductListRepository {

    override suspend fun getProductsList(): MVVMResult<ProductListModel?>{
        return try {
            MVVMResult.Success(api.getProductsList("https://mobile-s3-test-assets.aws-sdlc-bonial.com/shelf.json")?.mapToModel())
        }catch (e: Exception){
            MVVMResult.Error(MVVMException(LoadingListIntent,e))
        }
    }
}
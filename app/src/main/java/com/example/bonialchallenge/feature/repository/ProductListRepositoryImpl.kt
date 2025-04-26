package com.example.bonialchallenge.feature.repository

import com.example.bonialchallenge.feature.ui.model.ProductListModel
import com.example.bonialchallenge.model.mapToModel
import com.example.bonialchallenge.network.ProductListApi
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(private val api: ProductListApi): ProductListRepository {

    override suspend fun getProductsList(): ProductListModel?{
       return api.getProductsList("https://mobile-s3-test-assets.aws-sdlc-bonial.com/shelf.json")?.mapToModel()
    }
}
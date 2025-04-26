package com.example.bonialchallenge.ui.repository

import com.example.bonialchallenge.model.ProductListModel
import com.example.bonialchallenge.network.ProductListApi
import javax.inject.Inject

class ProductListRepository @Inject constructor(private val api: ProductListApi) {

    suspend fun call(): ProductListModel?{

       return api.getProductsList("https://mobile-s3-test-assets.aws-sdlc-bonial.com/shelf.json")
    }
}
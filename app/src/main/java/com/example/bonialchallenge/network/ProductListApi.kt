package com.example.bonialchallenge.network

import com.example.bonialchallenge.model.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductListApi {

    @GET
    suspend fun getProductsList(@Url url: String): ProductListResponse?

}
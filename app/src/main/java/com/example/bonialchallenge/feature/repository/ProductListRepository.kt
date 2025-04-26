package com.example.bonialchallenge.feature.repository

import com.example.bonialchallenge.feature.ui.model.ProductListModel

interface ProductListRepository {
    suspend fun getProductsList(): ProductListModel?
}
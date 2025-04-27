package com.example.bonialchallenge.presentation.feature.repository

import com.example.bonialchallenge.presentation.feature.ui.model.ProductListModel
import com.example.bonialchallenge.network.MVVMResult

interface ProductListRepository {
    suspend fun getProductsList(): MVVMResult<ProductListModel?>
}
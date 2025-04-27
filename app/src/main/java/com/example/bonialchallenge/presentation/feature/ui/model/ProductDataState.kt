package com.example.bonialchallenge.presentation.feature.ui.model

import com.example.bonialchallenge.presentation.feature.ui.model.ProductListModel.ContentDetailModel
import com.example.bonialchallenge.network.MVVMResult

data class ProductDataState (
    val productList: MVVMResult<ProductListModel?>
)

data class ProductUiState(
    val contents: List<ContentDetailModel>? = null,
    val isLoading: Boolean,
    val error: MVVMResult.Error? = null
)
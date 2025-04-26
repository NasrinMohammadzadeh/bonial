package com.example.bonialchallenge.feature.ui.model

import com.example.bonialchallenge.feature.ui.model.ProductListModel.ContentDetailModel

data class ProductDataState (
    val productList: ProductListModel?
)

data class ProductUiState(
    val contents: List<ContentDetailModel>? = null
)
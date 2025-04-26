package com.example.bonialchallenge.feature.viewmodel

import com.example.bonialchallenge.feature.ui.model.ProductUiState

internal fun ProductListViewModel.transformDataToUiModel(): ProductUiState {
    dataState.value?.let {
            return ProductUiState(
                contents = it.productList?.contents
            )
    } ?: run { return ProductUiState() }
}
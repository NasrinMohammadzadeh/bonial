package com.example.bonialchallenge.presentation.feature.viewmodel

import com.example.bonialchallenge.presentation.feature.ui.model.ProductListModel
import com.example.bonialchallenge.presentation.feature.ui.model.ProductUiState
import com.example.bonialchallenge.network.MVVMResult

internal fun ProductListViewModel.transformDataToUiModel(): ProductUiState {
    dataState.value?.let {
            return generateContent(it.productList)
    } ?: run { return ProductUiState(isLoading = false) }
}

private fun generateContent(dataState: MVVMResult<ProductListModel?>): ProductUiState {
    return when(dataState){
        is MVVMResult.Success -> ProductUiState(dataState.data?.contents,isLoading = false)
        is MVVMResult.Error -> ProductUiState(null,isLoading = false,dataState)
        is MVVMResult.Loading -> ProductUiState(null,isLoading = true)
    }
}
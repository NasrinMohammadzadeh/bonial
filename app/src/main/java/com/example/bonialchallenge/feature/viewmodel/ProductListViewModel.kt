package com.example.bonialchallenge.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bonialchallenge.feature.ui.model.ProductDataState
import com.example.bonialchallenge.feature.ui.model.ProductUiState
import com.example.bonialchallenge.feature.usecase.ProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel@Inject constructor(private val productListUseCase: ProductListUseCase): ViewModel(){

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState

    private val _dataState: MutableStateFlow<ProductDataState?> = MutableStateFlow(null)
    internal val dataState: StateFlow<ProductDataState?> = _dataState


    fun call() {
        viewModelScope.launch {
            val getProductListUseCase = async {
                productListUseCase.invoke()
            }
            _dataState.emit(getProductListUseCase.await())

            _uiState.emit(transformDataToUiModel())
        }
    }
}
package com.example.bonialchallenge.presentation.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bonialchallenge.presentation.feature.ui.model.ProductDataState
import com.example.bonialchallenge.presentation.feature.ui.model.ProductUiState
import com.example.bonialchallenge.presentation.feature.usecase.ProductListUseCase
import com.example.bonialchallenge.network.LoadingListIntent
import com.example.bonialchallenge.network.MVVMException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel@Inject constructor(private val productListUseCase: ProductListUseCase): ViewModel(){

    private val _uiState = MutableStateFlow(ProductUiState(isLoading = true))
    val uiState: StateFlow<ProductUiState> = _uiState

    private val _dataState: MutableStateFlow<ProductDataState?> = MutableStateFlow(null)
    internal val dataState: StateFlow<ProductDataState?> = _dataState


    fun call() {
        viewModelScope.launch {
            _uiState.emit(ProductUiState(isLoading = true))
            val getProductListUseCase = async {
                productListUseCase.invoke()
            }
            _dataState.emit(getProductListUseCase.await())

            _uiState.emit(transformDataToUiModel())
        }
    }

    fun onRetry(error: MVVMException?) {
        when (error?.intent) {
            is LoadingListIntent -> {
                call()
            }
            else -> {}
        }
    }
}
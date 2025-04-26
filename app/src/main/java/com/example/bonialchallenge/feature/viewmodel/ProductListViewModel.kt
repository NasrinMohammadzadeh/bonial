package com.example.bonialchallenge.feature.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bonialchallenge.feature.ui.model.ProductDataState
import com.example.bonialchallenge.feature.ui.model.ProductUiState
import com.example.bonialchallenge.feature.usecase.ProductListUseCase
import com.example.bonialchallenge.network.LoadingListIntent
import com.example.bonialchallenge.network.MVVMException
import com.example.bonialchallenge.network.MVVMIntent
import com.example.bonialchallenge.network.NetworkUtil
import com.example.bonialchallenge.ui.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
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

    private val _error = MutableStateFlow<MVVMException?>(null)
    val error: StateFlow<MVVMException?> = _error

    private val _isLoading = MutableStateFlow(Loading(false))
    val isLoading: StateFlow<Loading> = _isLoading

    private var status: MutableLiveData<Int> = MutableLiveData()

    fun call() {
        CoroutineScope(NetworkUtil.getCoroutineContext(status = status)).launch {
            showLoading(Loading(true))
            val getProductListUseCase = async {
                productListUseCase.invoke()
            }
            _dataState.emit(getProductListUseCase.await())

            _uiState.emit(transformDataToUiModel())
            showLoading(Loading(false))

        }
    }

    fun onRetry(error: MVVMException?) {
        clearError()
        when (error?.intent) {
            is LoadingListIntent -> call()
            else -> {}
        }
    }

     private fun clearError() {
        viewModelScope.launch {
            _error.emit(null)
        }
    }

    private suspend fun showLoading(loadingState: Loading) {
        _isLoading.emit(loadingState)
    }

    suspend fun setError(exception: Exception, intent: MVVMIntent) {
        _error.emit(MVVMException(intent, exception))
    }
}
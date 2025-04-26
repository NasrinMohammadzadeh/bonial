package com.example.bonialchallenge.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bonialchallenge.ui.repository.ProductListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel@Inject constructor(private val repository: ProductListRepository): ViewModel(){
    fun call() {
        viewModelScope.launch {
            val result = repository.call()
            result?.embedded?.contents?.forEach {
                Log.d(
                    "product",
                    it.contentType
                )
            }
        }
    }
}
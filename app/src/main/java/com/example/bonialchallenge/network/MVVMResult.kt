package com.example.bonialchallenge.network

sealed class MVVMResult<out R> {

    data class Success<out T>(val data: T) : MVVMResult<T>()

    data class Error(val baseError: MVVMException) : MVVMResult<Nothing>()

    object Loading : MVVMResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[baseError=$baseError]"
            Loading -> "Loading"
        }
    }
}
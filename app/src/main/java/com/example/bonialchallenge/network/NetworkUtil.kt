package com.example.bonialchallenge.network

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object NetworkUtil {

  fun getCoroutineContext(status: MutableLiveData<Int>? = null): CoroutineContext{
    return Dispatchers.IO + NetworkExceptionHandler().getInstance(status = status)
  }
}

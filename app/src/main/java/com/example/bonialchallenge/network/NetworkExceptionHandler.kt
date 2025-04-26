package com.example.bonialchallenge.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bonialchallenge.BuildConfig
import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.SocketTimeoutException
class NetworkExceptionHandler {
  fun getInstance(status: MutableLiveData<Int>? = null) = CoroutineExceptionHandler { _, throwable ->
    if (BuildConfig.DEBUG)
      throwable.message?.let { Log.e("OkHttp", it) }
    status?.postValue(RequestStatus.CALL_FAILURE.get())

    if (throwable is SocketTimeoutException)
      status?.postValue(RequestStatus.CALL_FAILURE.get())
  }
}

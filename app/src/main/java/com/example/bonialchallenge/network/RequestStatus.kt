package com.example.bonialchallenge.network

enum class RequestStatus (private val intValue: Int) {
  LOADING(0),
  CALL_SUCCESS(1),
  CALL_FAILURE(2);

  fun get(): Int {
    return intValue
  }
}

package com.example.bonialchallenge.network

import java.io.Serializable

data class MVVMException(val intent: MVVMIntent, val throwable: Throwable) : Exception(), Serializable

interface MVVMIntent


object LoadingListIntent : MVVMIntent
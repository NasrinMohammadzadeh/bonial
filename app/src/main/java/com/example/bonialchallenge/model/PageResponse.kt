package com.example.bonialchallenge.model

data class PageResponse(
    val size: Long,
    val totalElements: Long,
    val totalPages: Long,
    val number: Long
)
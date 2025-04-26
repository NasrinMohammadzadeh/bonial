package com.example.bonialchallenge.model

data class LinksResponse(
    val self: SelfResponse
){
    data class SelfResponse(
        val href: String
    )
}
package com.example.bonialchallenge.model

data class Embedded(
    val contents: List<Content>
){
    data class Content(
        val placement: String,
        val adFormat: String?,
        val contentFormatSource: String,
        val contentType: String,
        val content: Any?,
        val externalTracking: ExternalTracking
    ){
        data class ExternalTracking(
            val impression: List<String>,
            val click: List<String>
        )
    }
}
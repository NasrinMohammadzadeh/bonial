package com.example.bonialchallenge.model

data class EmbeddedResponse(
    val contents: List<ContentResponse>
){
    data class ContentResponse(
        val placement: String,
        val adFormat: String?,
        val contentFormatSource: String,
        val contentType: String,
        val content: Any?,
        val externalTracking: ExternalTrackingResponse
    ){
        data class ExternalTrackingResponse(
            val impression: List<String>,
            val click: List<String>
        )
    }
}
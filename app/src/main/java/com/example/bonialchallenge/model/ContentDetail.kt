package com.example.bonialchallenge.model

data class ContentDetail(
    val id: Long,
    val contentId: String,
    val title: String,
    val validFrom: String,
    val validUntil: String,
    val publishedFrom: String,
    val publishedUntil: String,
    val type: String,
    val brochureImage: String,
    val pageCount: Long,
    val publisher: Publisher,
    val contentBadges: List<Any?>,
    val distance: Double,
    val hideValidityDate: Boolean,
    val closestStore: ClosestStore
){
    data class ClosestStore(
        val id: String,
        val latitude: Double,
        val longitude: Double,
        val street: String,
        val streetNumber: String,
        val zip: String,
        val city: String
    )

    data class Publisher(
        val id: String,
        val name: String,
        val type: String
    )
}

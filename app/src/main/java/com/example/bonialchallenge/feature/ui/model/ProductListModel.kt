package com.example.bonialchallenge.feature.ui.model

data class ProductListModel(
    val contents: List<ContentDetailModel>
){
    data class ContentDetailModel(
        val id: Long,
        val title: String,
//        val validFrom: String,
//        val validUntil: String,
//        val publishedFrom: String,
//        val publishedUntil: String,
//        val type: String,
//        val brochureImage: String,
//        val pageCount: Long,
//        val publisher: PublisherModel,
//        val contentBadges: List<Any?>,
//        val distance: Double,
//        val hideValidityDate: Boolean,
//        val closestStore: ClosestStoreModel
    )
//    {
//        data class ClosestStoreModel(
//            val id: String,
//            val latitude: Double,
//            val longitude: Double,
//            val street: String,
//            val streetNumber: String,
//            val zip: String,
//            val city: String
//        )
//
//        data class PublisherModel(
//            val id: String,
//            val name: String,
//            val type: String
//        )
//    }
}

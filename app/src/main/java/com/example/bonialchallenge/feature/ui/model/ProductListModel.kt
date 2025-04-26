package com.example.bonialchallenge.feature.ui.model

import androidx.compose.runtime.Stable

@Stable
data class ProductListModel(
    val contents: List<ContentDetailModel>
) {
    @Stable
    data class ContentDetailModel(
        val id: Long,
        val title: String,
        val type: String,
        val brochureImage: String?,
        val publisher: PublisherModel,
    )

    @Stable
    data class PublisherModel(
        val name: String
    )
}

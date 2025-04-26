package com.example.bonialchallenge.model

import com.google.gson.annotations.SerializedName

data class ProductListModel(
    @SerializedName("_embedded") val embedded: Embedded,
    @SerializedName("_links") val links: Links,
    val page: Page
)

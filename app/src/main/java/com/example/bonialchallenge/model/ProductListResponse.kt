package com.example.bonialchallenge.model

import com.example.bonialchallenge.feature.ui.model.ProductListModel
import com.example.bonialchallenge.feature.ui.model.ProductListModel.ContentDetailModel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("_embedded") val embedded: EmbeddedResponse,
    @SerializedName("_links") val links: LinksResponse,
    val page: PageResponse
)

fun ProductListResponse.mapToModel(): ProductListModel{
       val list =  embedded.contents.filter { it.contentType == "brochure" || it.contentType == "brochurePremium"  }
            .map { item ->
                val content = Gson().fromJson(
                    Gson().toJson(item.content),
                    ContentDetailResponse::class.java
                )

                ContentDetailModel(
                    content.id,
                    content.title
                )
            }

    return ProductListModel(
        contents = list
    )
}

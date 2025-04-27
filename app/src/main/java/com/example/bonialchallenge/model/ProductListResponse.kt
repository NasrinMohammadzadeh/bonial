package com.example.bonialchallenge.model

import com.example.bonialchallenge.presentation.feature.ui.model.ProductListModel
import com.example.bonialchallenge.presentation.feature.ui.model.ProductListModel.ContentDetailModel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("_embedded") val embedded: EmbeddedResponse,
    @SerializedName("_links") val links: LinksResponse,
    val page: PageResponse
)

fun ProductListResponse.mapToModel(): ProductListModel {
       val list =  embedded.contents.filter { it.contentType == "brochure" || it.contentType == "brochurePremium"  }
            .map { item ->
                val content = Gson().fromJson(
                    Gson().toJson(item.content),
                    ContentDetailResponse::class.java
                )

                ContentDetailModel(
                    id = content.id,
                    title = content.title,
                    brochureImage = content.brochureImage,
                    type = item.contentType,
                    publisher = ProductListModel.PublisherModel(
                        content.publisher.name
                    )
                )
            }

    return ProductListModel(
        contents = list
    )
}

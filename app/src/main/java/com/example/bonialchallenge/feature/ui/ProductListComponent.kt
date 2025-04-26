package com.example.bonialchallenge.feature.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bonialchallenge.feature.ui.model.ProductListModel.*
import com.example.bonialchallenge.feature.ui.model.ProductUiState
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.bonialchallenge.R

@Composable
fun ProductListComponent(uiState: ProductUiState) {

    uiState.contents?.let {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(start = 8.dp, top = 8.dp)
        ) {
            items(
                it.chunked(2)
            ) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (rowItems.size == 1 && rowItems.first().type == "brochurePremium") {
                        PremiumItem(rowItems.first(), Modifier.fillMaxWidth())
                    } else if (rowItems.any { it.type == "brochurePremium" }) {
                        Column(
                            modifier = Modifier.padding(end = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            rowItems.forEach { item ->
                                PremiumItem(item, Modifier.fillMaxWidth())
                            }
                        }
                    } else {
                        rowItems.forEach { item ->
                            NormalItem(item, Modifier.weight(1f))
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PremiumItem(content: ContentDetailModel, modifier: Modifier) {
    Card(
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(300.dp),
                painter = rememberAsyncImagePainter(content.brochureImage),
                contentDescription = content.title,
                contentScale = ContentScale.Crop,

                )
            Text(
                modifier = Modifier.weight(1f).padding(horizontal = 10.dp),
                text = "🌟${content.publisher.name}"
            )
        }
    }
}

@Composable
fun NormalItem(content: ContentDetailModel, modifier: Modifier) {
    Card(
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                painter = if (content.brochureImage.isNullOrEmpty()) painterResource(R.drawable.placeholder_portrait) else rememberAsyncImagePainter(content.brochureImage),
                contentDescription = content.title,
                contentScale = ContentScale.Fit,

                )
            Text(
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                text = content.publisher.name
            )
        }
    }
}
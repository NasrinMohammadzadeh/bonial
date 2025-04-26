package com.example.bonialchallenge.feature.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bonialchallenge.feature.ui.model.ProductUiState

@Composable
fun ProductListComponent(modifier: Modifier, uiState: ProductUiState) {
    val state = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = state
    ) {
        uiState.contents?.forEach { content ->
            item {
                Content(content.title)
            }
        }
    }
}

@Composable
fun Content(text: String) {
    Text(
        modifier = Modifier,
        text = text
    )
}
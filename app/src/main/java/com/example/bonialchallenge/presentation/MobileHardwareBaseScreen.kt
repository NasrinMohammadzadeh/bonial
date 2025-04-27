package com.example.bonialchallenge.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bonialchallenge.network.MVVMException
import com.example.bonialchallenge.presentation.theme.MyApplicationTheme

@Composable
fun MobileHardwareBaseScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    loading: @Composable () -> Unit,
    error: @Composable () -> Unit,
    loadingState: Loading,
    exception: MVVMException?
) {
    MyApplicationTheme {
        Box(modifier = modifier) {
            content()
            if (loadingState.isLoading) loading()
            if (exception != null) error()
        }
    }
}
data class Loading(val isLoading: Boolean, val aplha: Float = 1f)
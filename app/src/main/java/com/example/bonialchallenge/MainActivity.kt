package com.example.bonialchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.bonialchallenge.feature.ui.LoadingScreen
import com.example.bonialchallenge.feature.ui.MobileHardwareApiError
import com.example.bonialchallenge.feature.ui.ProductListScreen
import com.example.bonialchallenge.feature.viewmodel.ProductListViewModel
import com.example.bonialchallenge.ui.MobileHardwareBaseScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val productListViewModel: ProductListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val exception by productListViewModel.error.collectAsState()
            val loadingState by productListViewModel.isLoading.collectAsState()

            MobileHardwareBaseScreen(
                content = { ProductListScreen(viewModel = productListViewModel) },
                loading = { LoadingScreen(Modifier.alpha(loadingState.aplha)) },
                error = {
                    MobileHardwareApiError(exception = exception,
                        onRetry = productListViewModel::onRetry)
                },
                loadingState = loadingState,
                exception = exception
            )
        }
    }

    override fun onResume() {
        super.onResume()
        productListViewModel.call()
    }
}

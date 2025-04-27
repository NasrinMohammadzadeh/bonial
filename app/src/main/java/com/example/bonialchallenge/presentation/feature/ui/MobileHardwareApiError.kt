package com.example.bonialchallenge.presentation.feature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bonialchallenge.R
import com.example.bonialchallenge.network.MVVMException
import com.example.bonialchallenge.presentation.theme.Typography
import com.example.bonialchallenge.presentation.theme.White

@Composable
fun MobileHardwareApiError(
    exception: MVVMException?,
    onRetry: (MVVMException?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = "Something is wrong",
            style = Typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(R.drawable.ic_mobile_hardware_api_error),
            contentDescription = null
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            content = {
                Text(
                    text = "Retry"
                )
            },
            onClick = {
                onRetry.invoke(exception)
            }
        )
        Spacer(modifier = Modifier.size(16.dp))
    }
}
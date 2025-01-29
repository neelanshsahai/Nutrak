package com.example.nutrak.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutrak.R
import com.example.nutrak.ui.theme.splashEndColor
import com.example.nutrak.ui.theme.splashStartColor

@Composable
fun SplashScreen(
    navigateToIntro: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToDashboard: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(splashStartColor, splashEndColor),
                )
            ),
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.nutrak_logo),
            contentDescription = stringResource(R.string.splash_logo),
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        navigateToIntro = { },
        navigateToLogin = { },
        navigateToDashboard = { },
    )
}
package com.example.nutrak.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ProfileTab() {
    Text(
        text = "PROFILE",
        modifier = Modifier.fillMaxSize().wrapContentHeight(Alignment.CenterVertically),
        textAlign = TextAlign.Center
    )
}
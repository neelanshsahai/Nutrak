package com.example.nutrak.ui.screens

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.ui.common.NutrakLoader
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.secondaryColor
import kotlinx.coroutines.delay

@Composable
fun ImageLoaderScreen() {
    var percentageProgress by remember { mutableIntStateOf(0) }
    LaunchedEffect(percentageProgress) {
        delay(30)
        percentageProgress += 1
    }

    Column(
        modifier = Modifier.fillMaxSize().background(AppTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        NutrakLoader(modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(0.5f))

        Text(
            text = "Scanning in progress ...",
            style = MaterialTheme.typography.labelMedium,
            color = AppTheme.colorScheme.onBackground
        )

        LinearProgressIndicator(
            progress = { percentageProgress.toFloat() / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = secondaryColor
        )

        Text(
            text = "$percentageProgress%",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = AppTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ImageLoaderScreenPreview() {
    NutrakTheme {
        ImageLoaderScreen()
    }
}
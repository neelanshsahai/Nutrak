package com.example.nutrak.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutrak.R
import com.example.nutrak.ui.theme.NutrakTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen() {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val backgroundImages = listOf(
        R.drawable.pager_one,
        R.drawable.pager_two,
        R.drawable.pager_three
    )

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(backgroundImages[page]),
                contentDescription = stringResource(R.string.intro_pager_background),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview
@Composable
fun IntroScreenPreview() {
    NutrakTheme {
        IntroScreen()
    }
}
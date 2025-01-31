package com.example.nutrak.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.common.NutrakButton
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(
    navigateToLogin: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    val backgroundImages = listOf(
        R.drawable.pager_one,
        R.drawable.pager_two,
        R.drawable.pager_three
    )

    val tipTitleList = listOf(
        "Track Your Nutrition Effortlessly",
        "Quick and Accurate Food Scans",
        "Build Healthy Habits with Streaks"
    )

    val tipDescriptionList = listOf(
        "Scan meals, get insights, and achieve health goals - eating healthy made simple",
        "Snap or upload a meal to get instant nutrition details with our smart AI technology",
        "Log meals, earn badges, and track streaks to celebrate your journey to better health!"
    )

    val verticalGradient = Brush.verticalGradient(
        0.00f to AppTheme.colorScheme.introLinearStart,
        0.40f to AppTheme.colorScheme.introLinearMid,
        0.80f to AppTheme.colorScheme.introLinearEnd
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
                contentScale = ContentScale.Crop
            )
        }

        Box(modifier = Modifier.fillMaxSize().background(verticalGradient))

        if (pagerState.currentPage != pagerState.pageCount - 1) {
            Text(
                text = "Skip  >>",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.TopEnd)
                    .clickable { navigateToLogin() }
            )
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HorizontalPagerIndicator(
                pageCount = pagerState.pageCount,
                currentPage = pagerState.currentPage,
                modifier = Modifier
            )

            Text(
                text = tipTitleList[pagerState.currentPage],
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = AppTheme.colorScheme.onBackground
            )

            Text(
                text = tipDescriptionList[pagerState.currentPage],
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = AppTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            ) {
                if (pagerState.currentPage != 0) {
                    NutrakButton(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                        buttonText = "<  Back",
                        isNegative = true
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                NutrakButton(
                    onClick = {
                        if (pagerState.currentPage == pagerState.pageCount - 1) {
                            navigateToLogin()
                        } else {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    buttonText = if (pagerState.currentPage == pagerState.pageCount - 1) {
                        "Get Started  >"
                    } else {
                        "Next   >"
                    }
                )
            }
        }
    }
}

@Composable
private fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.fillMaxWidth().fillMaxHeight(0.7f)
    ) {
        repeat(pageCount) { page ->
            val (color, width) = if (page == currentPage) {
                AppTheme.colorScheme.paginationActive to 16.dp
            } else {
                AppTheme.colorScheme.paginationInactive to 8.dp
            }

            Box(modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 4.dp
                )
                .clip(RoundedCornerShape(12.dp))
                .background(color)
                .width(width)
                .height(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun IntroScreenPreview() {
    NutrakTheme {
        IntroScreen(
            navigateToLogin = { }
        )
    }
}
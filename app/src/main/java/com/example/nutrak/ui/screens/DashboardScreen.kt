package com.example.nutrak.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.common.NutrakToolbar
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.primaryColor
import com.example.nutrak.ui.theme.secondaryColor
import com.example.nutrak.ui.theme.tertiaryColor
import com.example.nutrak.ui.viewmodels.ConsumptionData
import com.example.nutrak.ui.viewmodels.DashboardUiState
import com.example.nutrak.ui.viewmodels.DashboardViewModel
import com.example.nutrak.ui.viewmodels.RecommendationData

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    val uiState = viewModel.uiState.collectAsState().value

    DashboardScreen(
        selectedTab = selectedTab,
        updateSelectedTab = { index -> selectedTab = index},
        navTitleList = viewModel.navTitleList,
        navIconList = viewModel.navIconList,
        populateMockRecommendationData = viewModel::populateMockRecommendations,
        populateMockConsumptionData = viewModel::populateMockConsumptionData,
        processImage = viewModel::processImage,
        uiState = uiState,
    )
}

@Composable
fun DashboardScreen(
    selectedTab: Int,
    updateSelectedTab: (Int) -> Unit,
    navIconList: List<Int>,
    navTitleList: List<String>,
    populateMockRecommendationData: () -> List<RecommendationData>,
    populateMockConsumptionData: () -> List<ConsumptionData>,
    processImage: (Bitmap) -> Unit,
    uiState: DashboardUiState,
) {
    Column(
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)
    ) {
        if (!uiState.isCameraOpen || !uiState.isImageProcessing) {
            NutrakToolbar(isShowHamburger = true, isShowSearch = true)
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            when (selectedTab) {
                0 -> {
                    HomeTab(
                        recommendationList = populateMockRecommendationData(),
                        consumptionDataList = populateMockConsumptionData()
                    )
                }

                1 -> {
                    LogsTab()
                }

                2 -> {
                    CameraTab(
                        processImage = processImage
                    )
                }

                3 -> {
                    StreaksTab()
                }

                4 -> {
                    ProfileTab()
                }
            }
        }

        if (!uiState.isCameraOpen || !uiState.isImageProcessing) {
            HorizontalDivider(
                thickness = 1.dp,
                color = AppTheme.colorScheme.divider
            )

            Row(
                modifier = Modifier.fillMaxWidth().height(64.dp)
            ) {
                repeat(navTitleList.size) { index ->
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .clickable {
                                updateSelectedTab(index)
                                uiState.isCameraOpen = index == 2
                            }
                            .background(
                                if (selectedTab == index) {
                                    AppTheme.colorScheme.divider
                                } else {
                                    AppTheme.colorScheme.background
                                }
                            ),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(navIconList[index]),
                            contentDescription = "",
                        )

                        if (index != 2) {
                            Text(
                                text = navTitleList[index],
                                style = MaterialTheme.typography.labelSmall,
                                color = AppTheme.colorScheme.onBackground,
                                fontWeight = if (selectedTab == index) {
                                    FontWeight.Bold
                                } else {
                                    FontWeight.Normal
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    if (uiState.isImageProcessing) {

    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    NutrakTheme {
        DashboardScreen(
            selectedTab = 0,
            updateSelectedTab = { },
            navIconList = listOf(
                R.drawable.home,
                R.drawable.logs,
                R.drawable.scan_button,
                R.drawable.streaks,
                R.drawable.profile
            ),
            navTitleList = listOf(
                "Home",
                "Logs",
                "Scan",
                "Streaks",
                "Profile"
            ),
            populateMockRecommendationData = { listOf(
                RecommendationData(image = R.drawable.recommendation_image_1, name = "Mexican Pasta", calories = 320, stars = 5, prepTime = 5, serving = 1),
                RecommendationData(image = R.drawable.recommendation_image_1, name = "Chicken Sauteed", calories = 250, stars = 4, prepTime = 8, serving = 1),
                RecommendationData(image = R.drawable.recommendation_image_1, name = "Mexican Pasta", calories = 320, stars = 5, prepTime = 5, serving = 1),
                RecommendationData(image = R.drawable.recommendation_image_1, name = "Chicken Sauteed", calories = 250, stars = 4, prepTime = 8, serving = 1),
            ) },
            populateMockConsumptionData = { listOf(
                ConsumptionData(colorCode = secondaryColor, name = "Protein", percentConsumption = 45),
                ConsumptionData(colorCode = primaryColor, name = "Carbs", percentConsumption = 50),
                ConsumptionData(colorCode = tertiaryColor, name = "Fats", percentConsumption = 60)
            ) },
            processImage = { },
            uiState = DashboardUiState()
        )
    }
}
package com.example.nutrak.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.ui.common.NutrakButton
import com.example.nutrak.ui.common.NutrakToolbar
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.secondaryColor
import com.example.nutrak.ui.viewmodels.PersonalDetailsState
import com.example.nutrak.ui.viewmodels.PersonalDetailsViewModel
import kotlinx.coroutines.launch

@Composable
fun PersonalDetailsScreen(
    viewModel: PersonalDetailsViewModel,
    navigateToDashboard: () -> Unit,
) {
    PersonalDetailsScreen(
        updateGender = viewModel::updateGender,
        updateHeight = viewModel::updateHeight,
        updateAge = viewModel::updateAge,
        updateWeight = viewModel::updateWeight,
        updateGoal = viewModel::updateGoal,
        updatePreference = viewModel::updatePreferences,
        navigateToDashboard = navigateToDashboard,
        personalDetailsState = viewModel.personalDetailsState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PersonalDetailsScreen(
    updateGender: (Int) -> Unit,
    updateHeight: (Int, Int) -> Unit,
    updateAge: (Int) -> Unit,
    updateWeight: (Int) -> Unit,
    updateGoal: (String) -> Unit,
    updatePreference: (Int, Int) -> Unit,
    navigateToDashboard: () -> Unit,
    personalDetailsState: PersonalDetailsState,
) {
    val pagerState = rememberPagerState(pageCount = { 6 })
    val coroutineScope = rememberCoroutineScope()

    NutrakToolbar(isShowBack = true)

    Column(
        modifier = Modifier
            .padding(top = 56.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LinearProgressIndicator(
                progress = { 1f * (pagerState.currentPage + 1) / pagerState.pageCount },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = secondaryColor
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> {
                    GenderPage(
                        updateGender = updateGender,
                        gender = personalDetailsState.gender.ordinal
                    )
                }
                1 -> {
                    HeightPage(
                        updateHeight = updateHeight,
                        height = personalDetailsState.height
                    )
                }
                2 -> {
                    AgePage(
                        updateAge = updateAge,
                        age = personalDetailsState.age
                    )
                }
                3 -> {
                    WeightPage(
                        updateWeight = updateWeight,
                        weight = personalDetailsState.weight
                    )
                }
                4 -> {
                    GoalPage(
                        updateGoal = updateGoal,
                        goal = personalDetailsState.goal
                    )
                }
                5 -> {
                    PreferencePage(
                        updatePreference = updatePreference,
                        dietary = personalDetailsState.dietaryPref.ordinal,
                        allergy = personalDetailsState.allergyPref.ordinal
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (pagerState.currentPage != 0) {
                NutrakButton(
                    onClick = { },
                    buttonText = "Previous",
                    modifier = Modifier.weight(1f),
                    isNegative = true
                )
            }

            NutrakButton(
                onClick = {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        navigateToDashboard()
                    }
                },
                buttonText = if (pagerState.currentPage == pagerState.pageCount - 1) {
                    "Finish Setup"
                } else {
                    "Next"
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalDetailsScreenPreview() {
    NutrakTheme {
        PersonalDetailsScreen(
            updateGender = { },
            updateHeight = { _, _ -> },
            updateAge = { },
            updateWeight = { },
            updateGoal = { },
            updatePreference = { _, _ -> },
            navigateToDashboard = { },
            personalDetailsState = PersonalDetailsState()
        )
    }
}

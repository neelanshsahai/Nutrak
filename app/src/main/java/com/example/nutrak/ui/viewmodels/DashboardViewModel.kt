package com.example.nutrak.ui.viewmodels

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutrak.R
import com.example.nutrak.ui.theme.primaryColor
import com.example.nutrak.ui.theme.secondaryColor
import com.example.nutrak.ui.theme.tertiaryColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    dataStore: DataStore<Preferences>
): ViewModel() {
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState = _uiState.asStateFlow()

    val navIconList = listOf(
        R.drawable.home,
        R.drawable.logs,
        R.drawable.scan_button,
        R.drawable.streaks,
        R.drawable.profile
    )

    val navTitleList = listOf(
        "Home",
        "Logs",
        "Scan",
        "Streaks",
        "Profile"
    )

    fun populateMockConsumptionData() = listOf(
        ConsumptionData(colorCode = secondaryColor, name = "Protein", percentConsumption = 45),
        ConsumptionData(colorCode = primaryColor, name = "Carbs", percentConsumption = 50),
        ConsumptionData(colorCode = tertiaryColor, name = "Fats", percentConsumption = 60)
    )

    fun populateMockRecommendations() = listOf(
        RecommendationData(image = R.drawable.recommendation_image_1, name = "Mexican Pasta", calories = 320, stars = 5, prepTime = 5, serving = 1),
        RecommendationData(image = R.drawable.recommendation_image_1, name = "Chicken Sauteed", calories = 250, stars = 4, prepTime = 8, serving = 1),
        RecommendationData(image = R.drawable.recommendation_image_1, name = "Mexican Pasta", calories = 320, stars = 5, prepTime = 5, serving = 1),
        RecommendationData(image = R.drawable.recommendation_image_1, name = "Chicken Sauteed", calories = 250, stars = 4, prepTime = 8, serving = 1),
    )

    private fun populateMockNutritionData() = NutritionResultsData(
        title = "Pepperoni Pizza",
        type = "Food",
        overview = listOf(
            Pair("Calories", "320 kcal")
        ),
        macroNutrients = listOf(
            Pair("Proteins", "13g"),
            Pair("Carbs", "35g"),
            Pair("Fats", "12g"),
        ),
        microNutrients = listOf(
            Pair("Iron", "10%"),
            Pair("Calcium", "20%"),
        ),
        weeklyNutritionData = listOf(
            Pair("S", 0.5f),
            Pair("M", 0.65f),
            Pair("T", 0.8f),
            Pair("W", 1f),
            Pair("T", 0.65f),
            Pair("F", 0.8f),
            Pair("S", 0.8f),
        )
    )

    fun processImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _uiState.update { DashboardUiState(isImageProcessing = true) }
            delay(4000)
            _uiState.update {
                DashboardUiState(
                    isImageProcessed = true,
                    nutritionData = populateMockNutritionData()
                )
            }
        }
    }
}

data class ConsumptionData(
    val colorCode: Color = Color.Unspecified,
    val name: String = "",
    val percentConsumption: Int = 0
)

data class RecommendationData(
    @DrawableRes val image: Int = 0,
    val name: String = "",
    val calories: Int = 0,
    val stars: Int = 0,
    val prepTime: Int = 0,
    val serving: Int = 0,
)

data class NutritionResultsData(
    val title: String = "",
    val type: String = "",
    val overview: List<Pair<String, String>> = listOf(),
    val macroNutrients: List<Pair<String, String>> = listOf(),
    val microNutrients: List<Pair<String, String>> = listOf(),
    val weeklyNutritionData: List<Pair<String, Float>> = listOf(),
)

data class StreaksData(
    val streakCount: Int = 0,
    val pastData: List<PastData> = listOf(),
    val achievements: List<Pair<Int, Boolean>> = listOf()
)

data class PastData(
    val day: String = "",
    val date: Int = 1,
    val isChecked: Boolean = false
)

data class DashboardUiState(
    val isImageProcessing: Boolean = false,
    val isImageProcessed: Boolean = false,
    var isCameraOpen: Boolean = false,
    var nutritionData: NutritionResultsData? = null
)

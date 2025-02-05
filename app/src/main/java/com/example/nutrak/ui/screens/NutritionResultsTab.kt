package com.example.nutrak.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.common.NutrakButton
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.secondaryColor
import com.example.nutrak.ui.viewmodels.NutritionResultsData
import com.example.nutrak.utils.NUTRITION_MAP

@Composable
fun NutritionResultsTab(
    nutritionResultsData: NutritionResultsData
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Box {
                    Image(
                        painter = painterResource(R.drawable.nutrition),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    0.0f to AppTheme.colorScheme.introLinearStart,
                                    0.6f to AppTheme.colorScheme.introLinearMid,
                                    1.0f to AppTheme.colorScheme.introLinearEnd
                                )
                            )
                    )
                }

                Text(
                    text = nutritionResultsData.type.uppercase(),
                    color = AppTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(AppTheme.colorScheme.divider)
                        .padding(horizontal = 8.dp)
                )

                Text(
                    text = nutritionResultsData.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = AppTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Nutritional Overview:",
                style = MaterialTheme.typography.titleSmall,
                color = AppTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        items(
            items = nutritionResultsData.overview,
        ) { item ->
            NutritionDetailsCard(
                icon = NUTRITION_MAP.getOrDefault(item.first, R.drawable.nutrition_calories),
                name = item.first,
                value = item.second
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Macronutrients",
                style = MaterialTheme.typography.titleSmall,
                color = AppTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        items(
            items = nutritionResultsData.macroNutrients
        ) { item ->
            NutritionDetailsCard(
                icon = NUTRITION_MAP.getOrDefault(item.first, R.drawable.nutrition_calories),
                name = item.first,
                value = item.second
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Micronutrients",
                style = MaterialTheme.typography.titleSmall,
                color = AppTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        items(
            items = nutritionResultsData.microNutrients
        ) { item ->
            NutritionDetailsCard(
                icon = NUTRITION_MAP.getOrDefault(item.first, R.drawable.nutrition_calories),
                name = item.first,
                value = item.second
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(AppTheme.colorScheme.divider)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Weekly Meal Nutrition",
                        style = MaterialTheme.typography.titleMedium,
                        color = AppTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        repeat(7) { index ->
                            Column(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                topStartPercent = 50,
                                                topEndPercent = 50
                                            )
                                        )
                                        .background(secondaryColor)
                                        .width(8.dp)
                                        .height((64 * nutritionResultsData.weeklyNutritionData[index].second).dp)
                                )
                                Text(
                                    text = nutritionResultsData.weeklyNutritionData[index].first,
                                    color = AppTheme.colorScheme.onBackground
                                )
                            }
                        }
                    }
                }

                NutrakButton(
                    onClick = { },
                    buttonText = "Save to Daily Log",
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = buildAnnotatedString {
                        append("Want more insights? ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Upgrade to Premium")
                        }
                    },
                    color = AppTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun NutritionDetailsCard(
    @DrawableRes icon: Int,
    name: String,
    value: String,
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth(0.5f)
            .background(AppTheme.colorScheme.divider)
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = name,
                color = AppTheme.colorScheme.onBackground
            )

            Text(
                text = value,
                color = AppTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NutritionResultsPreview() {
    NutrakTheme {
        NutritionResultsTab(NutritionResultsData(
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
        ))
    }
}
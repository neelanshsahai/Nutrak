package com.example.nutrak.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.greenShadow
import com.example.nutrak.ui.theme.primaryColor
import com.example.nutrak.ui.theme.secondaryColor
import com.example.nutrak.ui.theme.tertiaryColor
import com.example.nutrak.ui.theme.yellowGreenShadow
import com.example.nutrak.ui.theme.yellowShadow
import com.example.nutrak.ui.viewmodels.ConsumptionData
import com.example.nutrak.ui.viewmodels.RecommendationData

@Composable
fun HomeTab(
    recommendationList: List<RecommendationData>,
    consumptionDataList: List<ConsumptionData>,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.person),
                contentDescription = stringResource(R.string.dashboard_person),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .height(56.dp)
                    .clip(CircleShape)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Good Morning, Ahmed!",
                    color = AppTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Track nutrition, stay healthy!Track nutrition, stay healthy!",
                    color = AppTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        HomeTabCard(
            title = "Scan Your Food",
            description = "Instantly analyze your meal’s nutrition by scanning it.",
            icon = R.drawable.scan_logo,
            backgroundColor = greenShadow
        )

        HomeTabCard(
            title = "Discover Healthy Recipes",
            description = "Find meals that fit your nutrition goals.",
            icon = R.drawable.recipes,
            backgroundColor = yellowShadow
        )

        CaloriesConsumedCard(
            consumptionDataList = consumptionDataList,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Recommended For You",
                color = AppTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "View All  >",
                color = AppTheme.colorScheme.onBackground,
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(4) { index ->
                Column(
                    modifier = Modifier
                        .height(192.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.dp,
                            color = AppTheme.colorScheme.divider,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(bottom = 8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(recommendationList[index].image),
                        contentDescription = stringResource(R.string.recommendations_image),
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
                        contentScale = ContentScale.Crop,
                    )

                    Text(
                        text = recommendationList[index].name,
                        color = AppTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.calories),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(AppTheme.colorScheme.onBackground)
                        )

                        Text(
                            text = "${recommendationList[index].calories} kcal per serving",
                            style = MaterialTheme.typography.bodySmall,
                            color = AppTheme.colorScheme.onBackground
                        )
                    }

                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth().height(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        repeat(5) { starIndex ->
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "",
                                tint = if (starIndex < recommendationList[index].stars) {
                                    primaryColor
                                } else {
                                    AppTheme.colorScheme.onBackground
                                }
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.clock),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(AppTheme.colorScheme.onBackground)
                        )

                        Text(
                            text = "${recommendationList[index].prepTime} min prep",
                            color = AppTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Image(
                            painter = painterResource(R.drawable.serve),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(AppTheme.colorScheme.onBackground),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Text(
                            text = "${recommendationList[index].serving} serving",
                            color = AppTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }

        HomeTabCard(
            title = "Health Tip of the Day",
            description = "Drinking water before meals can help you stay hydrated and eat just the right amount!",
            icon = R.drawable.water_glass,
            backgroundColor = yellowGreenShadow,
        )
    }
}

@Composable
fun CaloriesConsumedCard(
    totalCompletion: Int = 56,
    totalCalories: Float = 568.1f,
    consumptionDataList: List<ConsumptionData>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = AppTheme.colorScheme.divider,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .aspectRatio(1f)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                val backgroundColor = AppTheme.colorScheme.divider

                Text(
                    text = "$totalCompletion%",
                    fontWeight = FontWeight.SemiBold,
                    color = AppTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val width = size.width
                    val height = size.height
                    repeat(3) { index ->
                        drawArc(
                            color = backgroundColor,
                            topLeft = Offset(32f * index, 32f * index),
                            startAngle = 0f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(width = 24f),
                            size = Size(width - (64f * index), height - (64f * index))
                        )

                        drawArc(
                            color = consumptionDataList[index].colorCode,
                            topLeft = Offset(32f * index, 32f * index),
                            startAngle = 270f,
                            sweepAngle = (3.6f * consumptionDataList[index].percentConsumption),
                            useCenter = false,
                            style = Stroke(width = 24f, cap = StrokeCap.Round),
                            size = Size(width - (64f * index), height - (64f * index))
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.weight(3f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Calories consumed",
                    color = AppTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "$totalCalories kcal",
                    color = AppTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                repeat(3) { index ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .width(8.dp)
                                .clip(CircleShape)
                                .background(color = consumptionDataList[index].colorCode)
                        )

                        Text(
                            text = consumptionDataList[index].name,
                            color = AppTheme.colorScheme.onBackground,
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "${consumptionDataList[index].percentConsumption}%",
                            color = AppTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colorScheme.divider,
            thickness = 1.dp
        )

        Text(
            text = "Keep going! You’re doing great!",
            style = MaterialTheme.typography.bodyMedium,
            color = AppTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun HomeTabCard(
    title: String,
    description: String,
    @DrawableRes icon: Int,
    backgroundColor: Color,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        ) {
            Text(
                text = title,
                color = AppTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = description,
                color = AppTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Image(
            painter = painterResource(icon),
            contentDescription = stringResource(R.string.home_tab_card_logo),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTabPreview() {
    NutrakTheme {
        HomeTab(
            recommendationList = listOf(
                RecommendationData(image = R.drawable.recommendation_image_1, name = "Mexican Pasta", calories = 320, stars = 5, prepTime = 5, serving = 1),
                RecommendationData(image = R.drawable.recommendation_image_1, name = "Chicken Sauteed", calories = 250, stars = 4, prepTime = 8, serving = 1),
                RecommendationData(image = R.drawable.recommendation_image_1, name = "Mexican Pasta", calories = 320, stars = 5, prepTime = 5, serving = 1),
                RecommendationData(image = R.drawable.recommendation_image_1, name = "Chicken Sauteed", calories = 250, stars = 4, prepTime = 8, serving = 1),
            ),
            consumptionDataList = listOf(
                ConsumptionData(colorCode = secondaryColor, name = "Protein", percentConsumption = 45),
                ConsumptionData(colorCode = primaryColor, name = "Carbs", percentConsumption = 50),
                ConsumptionData(colorCode = tertiaryColor, name = "Fats", percentConsumption = 60)
            )
        )
    }
}
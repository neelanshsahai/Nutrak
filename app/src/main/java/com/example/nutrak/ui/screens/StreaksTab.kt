package com.example.nutrak.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.secondaryColor
import com.example.nutrak.ui.viewmodels.PastData
import com.example.nutrak.ui.viewmodels.StreaksData

@Composable
fun StreaksTab(streaksData: StreaksData) {
    val iconsList = listOf(
        R.drawable.silver,
        R.drawable.bronze,
        R.drawable.gold,
        R.drawable.platinum
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Box {
            Image(
                painter = painterResource(R.drawable.streaks_logo),
                contentDescription = "",
            )

            Text(
                text = streaksData.streakCount.toString(),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 8.dp).align(Alignment.BottomCenter)
            )
        }

        Text(
            text = buildAnnotatedString {
                append("You're on a \n")
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )) {
                    append("${streaksData.streakCount} day Streak! \n")
                }
                append("Keep it up!")
            },
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackground
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).border(1.dp, AppTheme.colorScheme.divider, RoundedCornerShape(12.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(7) { index ->
                Column(
                    modifier = Modifier.padding(vertical = 16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = streaksData.pastData[index].day,
                        color = secondaryColor,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontWeight = FontWeight.SemiBold
                    )

                    if (streaksData.pastData[index].isChecked) {
                        Image(
                            painter = painterResource(R.drawable.streak_day),
                            contentDescription = ""
                        )
                    } else {
                        Text(
                            text = streaksData.pastData[index].date.toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .size(28.dp)
                                .wrapContentHeight()
                                .border(1.dp, AppTheme.colorScheme.divider, CircleShape)
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Milestones",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                color = AppTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
            )

            repeat(4) { index ->
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth().height(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(AppTheme.colorScheme.divider)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(iconsList[index]),
                        contentDescription = "",
                    )

                    Text(
                        text = "${streaksData.achievements[index].first}-day streak achiever",
                        color = AppTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    if (streaksData.achievements[index].second) {
                        Image(
                            painter = painterResource(R.drawable.check_circle),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StreaksPreview() {
    NutrakTheme {
        StreaksTab(
            StreaksData(
                streakCount = 5,
                pastData = listOf(
                    PastData("M", 30, false),
                    PastData("T", 31, true),
                    PastData("W", 1, true),
                    PastData("T", 2, true),
                    PastData("F", 3, true),
                    PastData("S", 4, true),
                    PastData("S", 5, false),
                ),
                achievements = listOf(
                    Pair(7, true),
                    Pair(10, false),
                    Pair(20, false),
                    Pair(30, false),
                )
            )
        )
    }
}
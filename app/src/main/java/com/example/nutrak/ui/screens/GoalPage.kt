package com.example.nutrak.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.primaryColor

@Composable
fun GoalPage(
    updateGoal: (String) -> Unit,
    goal: String = ""
) {
    val goalIconList = listOf(
        R.drawable.weight_loss,
        R.drawable.maintain_weight,
        R.drawable.gain_weight,
        R.drawable.gain_muscle
    )

    val goalList = listOf(
        "Weight loss",
        "Maintain Weight",
        "Gain Weight",
        "Build Muscle"
    )

    var selectedIndex by remember { mutableIntStateOf(goalList.indexOf(goal)) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.goal),
            contentDescription = stringResource(R.string.what_is_your_goal),
            modifier = Modifier.fillMaxHeight(0.2f)
        )

        Text(
            text = "What's your goal?",
            style = MaterialTheme.typography.titleLarge,
            color = AppTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Choose your goal to tailor your nutrition insights.",
            style = MaterialTheme.typography.bodyMedium,
            color = AppTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        repeat(4) { index ->
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = if (selectedIndex == index) {
                            2.dp
                        } else {
                            1.dp
                        },
                        color = if (selectedIndex == index) {
                            primaryColor
                        } else {
                            AppTheme.colorScheme.divider
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(AppTheme.colorScheme.divider)
                    .clickable {
                        selectedIndex = index
                        updateGoal(goalList[index])
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(goalIconList[index]),
                    contentDescription = stringResource(R.string.goal_logo),
                    colorFilter = ColorFilter.tint(if (selectedIndex == index) {
                        primaryColor
                    } else {
                        AppTheme.colorScheme.onBackground
                    }),
                    modifier = Modifier.padding(horizontal = 16.dp)

                )

                Text(
                    text = goalList[index],
                    color = AppTheme.colorScheme.onBackground
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun GoalPagePreview() {
    NutrakTheme {
        GoalPage(
            updateGoal = { }
        )
    }
}
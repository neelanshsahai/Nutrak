package com.example.nutrak.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
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
fun GenderPage(
    updateGender: (Int) -> Unit,
    gender: Int = -1
) {
    var selectedGender by remember { mutableIntStateOf(gender) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.gender),
            contentDescription = stringResource(R.string.gender_page_logo),
            modifier = Modifier.fillMaxHeight(0.2f)
        )

        Text(
            text = "What is your gender?",
            style = MaterialTheme.typography.titleLarge,
            color = AppTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(2) { index ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1.5f)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = if (selectedGender == index) {
                                2.dp
                            } else {
                                1.dp
                            },
                            color = if (selectedGender == index) {
                                primaryColor
                            } else {
                                AppTheme.colorScheme.divider
                            },
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(AppTheme.colorScheme.divider)
                        .padding(16.dp)
                        .clickable {
                            selectedGender = index
                            updateGender(index)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(if (index == 0) {
                            R.drawable.male
                        } else {
                            R.drawable.female
                        }),
                        contentDescription = stringResource(R.string.gender_logo),
                        modifier = Modifier.weight(1f),
                        colorFilter = ColorFilter.tint(
                            if (selectedGender == index) {
                                primaryColor
                            } else {
                                AppTheme.colorScheme.onBackground
                            }
                        )
                    )

                    Text(
                        text = if (index == 0) {
                            "Male"
                        } else {
                            "Female"
                        },
                        color = AppTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenderPagePreview() {
    NutrakTheme {
        GenderPage(
            updateGender = { }
        )
    }
}
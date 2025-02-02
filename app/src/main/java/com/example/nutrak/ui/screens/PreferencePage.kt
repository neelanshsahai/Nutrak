package com.example.nutrak.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun PreferencePage(
    updatePreference: (Int, Int) -> Unit,
    dietary: Int = -1,
    allergy: Int = -1,
) {
    var selectedDietaryPreference by remember { mutableIntStateOf(dietary) }
    var selectedAllergy by remember { mutableIntStateOf(allergy) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.preferences),
            contentDescription = stringResource(R.string.preference_page_logo),
            modifier = Modifier.fillMaxHeight(0.2f)
        )

        Text(
            text = "Any preferences or restrictions?",
            style = MaterialTheme.typography.titleLarge,
            color = AppTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Let’s make sure your nutrition advice fits your lifestyle.",
            style = MaterialTheme.typography.bodyMedium,
            color = AppTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Dietary Preferences",
            style = MaterialTheme.typography.labelLarge,
            color = AppTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(2) { index ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1.75f)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = if (selectedDietaryPreference == index) {
                                2.dp
                            } else {
                                1.dp
                            },
                            color = if (selectedDietaryPreference == index) {
                                primaryColor
                            } else {
                                AppTheme.colorScheme.divider
                            },
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(AppTheme.colorScheme.divider)
                        .padding(16.dp)
                        .clickable {
                            selectedDietaryPreference = index
                            updatePreference(selectedDietaryPreference, selectedAllergy)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(if (index == 0) {
                            R.drawable.vegan
                        } else {
                            R.drawable.keto
                        }),
                        contentDescription = stringResource(R.string.gender_logo),
                        modifier = Modifier.weight(1f),
                        colorFilter = ColorFilter.tint(
                            if (selectedDietaryPreference == index) {
                                primaryColor
                            } else {
                                AppTheme.colorScheme.onBackground
                            }
                        )
                    )

                    Text(
                        text = if (index == 0) {
                            "Vegan"
                        } else {
                            "Keto"
                        },
                        color = AppTheme.colorScheme.onBackground
                    )
                }
            }
        }

        Text(
            text = "Allergies",
            style = MaterialTheme.typography.labelLarge,
            color = AppTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(2) { index ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1.75f)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = if (selectedAllergy == index) {
                                2.dp
                            } else {
                                1.dp
                            },
                            color = if (selectedAllergy == index) {
                                primaryColor
                            } else {
                                AppTheme.colorScheme.divider
                            },
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(AppTheme.colorScheme.divider)
                        .padding(16.dp)
                        .clickable {
                            selectedAllergy = index
                            updatePreference(selectedDietaryPreference, selectedAllergy)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(if (index == 0) {
                            R.drawable.gluten_free
                        } else {
                            R.drawable.nut_free
                        }),
                        contentDescription = stringResource(R.string.gender_logo),
                        modifier = Modifier.weight(1f),
                        colorFilter = ColorFilter.tint(
                            if (selectedAllergy == index) {
                                primaryColor
                            } else {
                                AppTheme.colorScheme.onBackground
                            }
                        )
                    )

                    Text(
                        text = if (index == 0) {
                            "Gluten-Free"
                        } else {
                            "Nut-Free"
                        },
                        color = AppTheme.colorScheme.onBackground
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreferencePagePreview() {
    NutrakTheme {
        PreferencePage(
            updatePreference = { _, _ -> }
        )
    }
}
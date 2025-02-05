package com.example.nutrak.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.nutrak.R
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.secondaryColor

@Composable
fun HeightPage(
    updateHeight: (Int, Int) -> Unit,
    height: Pair<Int, Int> = Pair(0, 0)
) {
    var footValue by remember { mutableIntStateOf(height.first) }
    var inchValue by remember { mutableIntStateOf(height.second) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.height),
            contentDescription = stringResource(R.string.what_is_your_height),
            modifier = Modifier.fillMaxHeight(0.2f)
        )

        Text(
            text = "What is your height?",
            style = MaterialTheme.typography.titleLarge,
            color = AppTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NumberPicker(
                value = footValue,
                onValueChange = {
                    footValue = it
                    updateHeight(footValue, inchValue)
                },
                range = 0..9,
                dividersColor = secondaryColor,
                textStyle = TextStyle(color = AppTheme.colorScheme.onBackground)
            )

            Text(text = "ft")

            NumberPicker(
                value = inchValue,
                onValueChange = {
                    inchValue = it
                    updateHeight(footValue, inchValue)
                },
                range = 0..11,
                dividersColor = secondaryColor,
                textStyle = TextStyle(color = AppTheme.colorScheme.onBackground)
            )

            Text(text = "in")
        }

        Image(
            painter = painterResource(R.drawable.height_image),
            contentDescription = stringResource(R.string.height_image)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeightPagePreview() {
    NutrakTheme {
        HeightPage(
            updateHeight = { _, _ -> },
        )
    }
}
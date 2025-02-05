package com.example.nutrak.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
fun WeightPage(
    updateWeight: (Int) -> Unit,
    weight: Int = 0
) {
    var selectedWeight by remember { mutableIntStateOf(weight) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.weight),
            contentDescription = stringResource(R.string.what_is_your_age),
            modifier = Modifier.fillMaxHeight(0.2f)
        )

        Text(
            text = "What is your weight?",
            style = MaterialTheme.typography.titleLarge,
            color = AppTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(R.drawable.weight_person),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.weight(1f))

            NumberPicker(
                value = selectedWeight,
                onValueChange = {
                    selectedWeight = it
                    updateWeight(selectedWeight)
                },
                dividersColor = secondaryColor,
                textStyle = TextStyle(color = AppTheme.colorScheme.onBackground),
                range = 0..200
            )

            Text(
                text = "kgs",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeightPagePreview() {
    NutrakTheme {
        WeightPage(
            updateWeight = { }
        )
    }
}
package com.example.nutrak.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.nutrak.R
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.secondaryColor

@Composable
fun AgePage(
    updateAge: (Int) -> Unit,
    age: Int = 0
) {
    var selectedAge by remember { mutableIntStateOf(age) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.age),
            contentDescription = stringResource(R.string.what_is_your_age),
            modifier = Modifier.fillMaxHeight(0.2f)
        )

        Text(
            text = "What is your age?",
            style = MaterialTheme.typography.titleLarge,
            color = AppTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.weight(1f))

        NumberPicker(
            value = selectedAge,
            onValueChange = {
                selectedAge = it
                updateAge(selectedAge)
            },
            range = 0..99,
            dividersColor = secondaryColor,
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}


@Preview(showBackground = true)
@Composable
fun AgePagePreview() {
    NutrakTheme {
        AgePage(
            updateAge = { }
        )
    }
}

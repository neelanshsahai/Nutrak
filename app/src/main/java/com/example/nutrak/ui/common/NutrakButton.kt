package com.example.nutrak.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme

@Composable
fun NutrakButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier,
    isNegative: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        border = if (isNegative) {
            BorderStroke(1.dp, AppTheme.colorScheme.onBackground)
        } else {
            BorderStroke(1.dp, Color(0xFF98671E))
        },
        shape = RoundedCornerShape(25),
        colors = if (isNegative) {
            ButtonColors(
                containerColor = AppTheme.colorScheme.background,
                contentColor = AppTheme.colorScheme.onBackground,
                disabledContentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified
            )
        } else {
            ButtonColors(
                containerColor = Color(0xFFFFA726) ,
                contentColor = Color.Black,
                disabledContainerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified
            )
        }
    ) {
        Text(modifier = Modifier.padding(horizontal = 8.dp), text = buttonText)
    }
//ffa725
}

@Preview
@Composable
fun NutrakButtonPreview() {
    NutrakTheme {
        NutrakButton(
            onClick = { },
            buttonText = "Next  >",
        )
    }
}
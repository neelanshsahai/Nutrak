package com.example.nutrak.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme

@Composable
fun ButtonWithLogo(
    onClick: () -> Unit,
    buttonText: String,
    buttonLogo: Painter,
    modifier: Modifier = Modifier,
    applyTint: Boolean = false,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(1.dp, AppTheme.colorScheme.iconButtonBorder),
        shape = RoundedCornerShape(25),
        colors = ButtonColors(
            containerColor = AppTheme.colorScheme.background,
            contentColor = AppTheme.colorScheme.onBackground,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        )
    ) {
        if (applyTint) {
            Image(
                painter = buttonLogo,
                contentDescription = stringResource(R.string.login_button_logo),
                colorFilter = ColorFilter.tint(AppTheme.colorScheme.onBackground)
            )
        } else {
            Image(
                painter = buttonLogo,
                contentDescription = stringResource(R.string.login_button_logo),
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = buttonText)
    }
}

@Preview
@Composable
fun ButtonWithLogoPreview() {
    NutrakTheme {
        ButtonWithLogo(
            onClick = { },
            buttonText = "Google",
            buttonLogo = painterResource(R.drawable.google),
        )
    }
}
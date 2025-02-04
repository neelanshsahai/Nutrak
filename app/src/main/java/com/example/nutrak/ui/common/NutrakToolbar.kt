package com.example.nutrak.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme

@Composable
fun NutrakToolbar(
    modifier: Modifier = Modifier,
    isShowBack: Boolean = false,
    isShowHamburger: Boolean = false,
    isShowSearch: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isShowBack) {
            Image(
                painter = painterResource(R.drawable.back_arrow),
                contentDescription = stringResource(R.string.back_arrow),
                colorFilter = ColorFilter.tint(AppTheme.colorScheme.onBackground)
            )
        }

        if (isShowHamburger) {
            Image(
                painter = painterResource(R.drawable.hamburger),
                contentDescription = stringResource(R.string.hamburger_menu),
                colorFilter = ColorFilter.tint(AppTheme.colorScheme.onBackground)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(R.drawable.nutrak_logo),
            contentDescription = stringResource(R.string.splash_logo)
        )

        Spacer(modifier = Modifier.weight(1f))

        if (isShowSearch) {
            Image(
                painter = painterResource(R.drawable.search),
                contentDescription = stringResource(R.string.search_icon),
                colorFilter = ColorFilter.tint(AppTheme.colorScheme.onBackground)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutrakToolbarPreview() {
    NutrakTheme {
        NutrakToolbar()
    }
}

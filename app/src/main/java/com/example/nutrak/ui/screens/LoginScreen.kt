package com.example.nutrak.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.common.ButtonWithLogo
import com.example.nutrak.ui.common.NutrakButton
import com.example.nutrak.ui.common.NutrakToolbar
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.primaryColor
import com.example.nutrak.ui.viewmodels.AuthUiState
import com.example.nutrak.ui.viewmodels.AuthenticationViewModel

@Composable
fun LoginScreen(
    viewModel: AuthenticationViewModel,
    navigateToSignUp: () -> Unit,
    navigateToPassword: (String) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    LoginScreen(
        signInWithGoogle = viewModel::signInWithGoogle,
        signInWithApple = viewModel::signInWithApple,
        navigateToSignUp = navigateToSignUp,
        navigateToPassword = navigateToPassword,
        validateEmail = viewModel::isEmailValid,
        showErrorToast = { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        },
        uiState = uiState,
    )
}

@Composable
fun LoginScreen(
    signInWithGoogle: () -> Unit,
    signInWithApple: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToPassword: (String) -> Unit,
    validateEmail: (String) -> Boolean,
    showErrorToast: (String) -> Unit,
    uiState: AuthUiState,
) {
    var email by remember { mutableStateOf("") }
    val verticalGradient = Brush.verticalGradient(
        0.00f to AppTheme.colorScheme.introLinearStart,
        0.60f to AppTheme.colorScheme.introLinearMid,
        1.00f to AppTheme.colorScheme.introLinearEnd
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.5f)
        ) {
            Image(
                painter = painterResource(R.drawable.login_background),
                contentDescription = stringResource(R.string.login_background),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Image(
                painter = painterResource(R.drawable.scan_preview),
                contentDescription = stringResource(R.string.scan_preview),
                modifier = Modifier.align(Alignment.Center)
            )

            Box(modifier = Modifier.fillMaxSize().background(verticalGradient))
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign in",
                style = MaterialTheme.typography.headlineSmall,
                color = AppTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Log in to track your nutrition and achieve your goals!",
                color = AppTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                ButtonWithLogo(
                    onClick = { signInWithGoogle() },
                    buttonText = "Google",
                    buttonLogo = painterResource(R.drawable.google),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                ButtonWithLogo(
                    onClick = { signInWithApple() },
                    buttonText = "Apple",
                    buttonLogo = painterResource(R.drawable.apple),
                    modifier = Modifier.weight(1f),
                    applyTint = true,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .weight(1f)
                        .background(AppTheme.colorScheme.divider)
                )

                Text(
                    text = "OR",
                    color = AppTheme.colorScheme.dividerText,
                    modifier = Modifier.padding(horizontal = 8.dp))

                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .weight(1f)
                        .background(AppTheme.colorScheme.divider)
                )
            }

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = { Text(text = "Enter your email") },
            )

            NutrakButton(
                onClick = {
                    if (validateEmail(email)) {
                        navigateToPassword(email)
                    } else {
                        showErrorToast("PLease enter an Email Id")
                    }
                },
                buttonText = "Continue",
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Don't have an account?",
                    color = AppTheme.colorScheme.onBackground
                )

                Text(
                    text = " Sign Up",
                    color = primaryColor,
                    modifier = Modifier.clickable {
                        navigateToSignUp()
                    }
                )
            }
        }
    }

    NutrakToolbar()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    NutrakTheme {
        LoginScreen(
            signInWithApple = { },
            signInWithGoogle = { },
            navigateToSignUp = { },
            navigateToPassword = { },
            validateEmail = { false },
            showErrorToast = { },
            uiState = AuthUiState()
        )
    }
}
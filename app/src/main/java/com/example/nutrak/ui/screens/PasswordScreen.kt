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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.common.NutrakButton
import com.example.nutrak.ui.common.NutrakToolbar
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.primaryColor
import com.example.nutrak.ui.viewmodels.AuthUiState
import com.example.nutrak.ui.viewmodels.AuthenticationViewModel

@Composable
fun PasswordScreen(
    viewModel: AuthenticationViewModel,
    emailId: String,
    navigateToSignUp: () -> Unit,
    navigateToDashboard: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    PasswordScreen(
        emailId = emailId,
        navigateToDashboard = navigateToDashboard,
        navigateToSignUp = navigateToSignUp,
        isPasswordValid = viewModel::isPasswordValid,
        login = viewModel::login,
        showErrorToast = { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        },
        uiState = uiState,
    )
}

@Composable
fun PasswordScreen(
    emailId: String,
    navigateToDashboard: () -> Unit,
    navigateToSignUp: () -> Unit,
    isPasswordValid: (String) -> Boolean,
    login: () -> Unit,
    showErrorToast: (String) -> Unit,
    uiState: AuthUiState,
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.8f))

        Image(
            painter = painterResource(R.drawable.person),
            contentDescription = stringResource(R.string.user_profile),
            modifier = Modifier
                .padding(16.dp)
                .clip(CircleShape)
        )

        Text(
            text = "Welcome back!",
            color = AppTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelSmall
        )

        Text(
            text = "Esther Howard",
            color = AppTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(50))
        ) {
            Text(
                text = emailId,
                color = AppTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .background(AppTheme.colorScheme.divider)
                    .padding(horizontal = 16.dp)
            )
        }

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Enter Password") },
            trailingIcon = {
                val trailingIcon = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = stringResource(R.string.toggle_password_visibility)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )

        Text(
            text = "Forgot Password?",
            color = Color.Blue,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
        )

        Spacer(modifier = Modifier.weight(1f))

        NutrakButton(
            onClick = {
                if (isPasswordValid(password)) {
                    login()
                } else {
                    showErrorToast("Please enter a password")
                }
            },
            buttonText = "Sign In",
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

//    if (uiState.isLoading) {
//        Show Loader
//    }

    if (uiState.isAuthSuccessful) {
        navigateToDashboard()
    } else if (uiState.errorMessage.isNotBlank()) {
        showErrorToast(uiState.errorMessage)
    }

    NutrakToolbar(isShowBack = true)
}

@Preview(showBackground = true)
@Composable
fun PasswordScreenPreview() {
    NutrakTheme {
        PasswordScreen(
            emailId = "abc@xyz.com",
            navigateToDashboard = { },
            navigateToSignUp = { },
            isPasswordValid = { false },
            uiState = AuthUiState(),
            showErrorToast = { },
            login = { },
        )
    }
}
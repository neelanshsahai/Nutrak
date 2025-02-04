package com.example.nutrak.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun SignUpScreen(
    viewModel: AuthenticationViewModel,
    navigateToSignInScreen: () -> Unit,
    navigateToVerifyScreen: (String) -> Unit,
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState().value

    SignUpScreen(
        checkFormData = viewModel::isFormDataValid,
        onSignUp = viewModel::signUp,
        navigateToSignInScreen = navigateToSignInScreen,
        navigateToVerifyScreen = navigateToVerifyScreen,
        showErrorToast = { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        },
        uiState = uiState,
    )
}

@Composable
fun SignUpScreen(
    checkFormData: (String, String, String, String) -> Boolean,
    onSignUp: () -> Unit,
    navigateToSignInScreen: () -> Unit,
    navigateToVerifyScreen: (String) -> Unit,
    showErrorToast: (String) -> Unit,
    uiState: AuthUiState,
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checkboxChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(top = 56.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.create_account),
                contentDescription = stringResource(R.string.create_account_person_logo),
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Create Your Account",
                color = AppTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = { Text("First Name") },
                modifier = Modifier.weight(1f)
            )

            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                placeholder = { Text("Last Name") },
                modifier = Modifier.weight(1f)
            )
        }

        TextField(
            value = emailId,
            onValueChange = { emailId = it },
            placeholder = { Text("Email ID") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = checkboxChecked,
                onCheckedChange = { checkboxChecked = !checkboxChecked}
            )

            Text(
                text = "I agree to the Terms of Service and Privacy Policy",
                style = MaterialTheme.typography.bodySmall
            )
        }

        NutrakButton(
            onClick = {
                if (checkboxChecked) {
                    if (checkFormData(firstName, lastName, emailId, password)) {
                        onSignUp()
                    } else {
                        showErrorToast("Please enter all the details to continue")
                    }
                } else {
                    showErrorToast("Please agree to the Terms and Policies")
                }
            },
            buttonText = "Create an Account",
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.navigationBars),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Already have an account?",
                color = AppTheme.colorScheme.onBackground
            )

            Text(
                text = " Sign In",
                color = primaryColor,
                modifier = Modifier.clickable {
                    navigateToSignInScreen()
                }
            )
        }
    }

    NutrakToolbar(isShowBack = true, modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars))

//    if (uiState.isLoading) {
        // Show Loader
//    }

    if (uiState.isAuthSuccessful) {
        navigateToVerifyScreen(emailId)
    } else {
        if (uiState.errorMessage.isNotBlank()) {
            showErrorToast(uiState.errorMessage)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    NutrakTheme {
        SignUpScreen(
            onSignUp = { },
            navigateToSignInScreen = { },
            checkFormData = { _, _, _, _ -> false },
            navigateToVerifyScreen = { },
            showErrorToast = { },
            uiState = AuthUiState()
        )
    }
}

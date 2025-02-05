package com.example.nutrak.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrak.R
import com.example.nutrak.ui.common.NutrakButton
import com.example.nutrak.ui.common.NutrakToolbar
import com.example.nutrak.ui.theme.AppTheme
import com.example.nutrak.ui.theme.NutrakTheme
import com.example.nutrak.ui.theme.primaryColor
import com.example.nutrak.ui.theme.secondaryColor
import com.example.nutrak.ui.viewmodels.AuthUiState
import com.example.nutrak.ui.viewmodels.AuthenticationViewModel

@Composable
fun VerifyEmailScreen(
    emailId: String,
    viewModel: AuthenticationViewModel,
    navigateToWelcomeScreen: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    VerifyEmailScreen(
        emailId = emailId,
        resendOtp = viewModel::resendOtp,
        validateOtp = viewModel::isOtpValid,
        submitOtp = viewModel::verifyLoginOtp,
        navigateToWelcomeScreen = navigateToWelcomeScreen,
        showErrorText = { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        },
        uiState = uiState
    )
}

@Composable
fun VerifyEmailScreen(
    emailId: String,
    resendOtp: () -> Unit,
    validateOtp: (String) -> Boolean,
    submitOtp: () -> Unit,
    navigateToWelcomeScreen: () -> Unit,
    showErrorText: (String) -> Unit,
    uiState: AuthUiState,
) {
    var otpValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(AppTheme.colorScheme.background)
            .padding(top = 56.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.email),
                contentDescription = stringResource(R.string.verify_your_email),
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Create Your Account",
                color = AppTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = buildAnnotatedString {
                append("Enter the OTP sent to ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append(emailId)
                }
            },
            style = MaterialTheme.typography.bodySmall
        )

        BasicTextField(
            value = otpValue,
            onValueChange = { otpValue = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            decorationBox = { OtpTextField(otpValue = otpValue) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            Text(
                text = "Didn't Received? ",
                color = AppTheme.colorScheme.onBackground
            )

            Text(
                text = "Resend",
                color = primaryColor,
                modifier = Modifier.clickable {
                    resendOtp()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        NutrakButton(
            onClick = {
                if (validateOtp(otpValue)) {
                    submitOtp()
                } else {
                    showErrorText("Please enter a 4 digit OTP")
                }
            },
            buttonText = "Submit Now",
            modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.navigationBars)
        )
    }

    NutrakToolbar(isShowBack = true, modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars))

//    if (uiState.isLoading) {
//        Show Loader
//    }

    if (uiState.isAuthSuccessful) {
        navigateToWelcomeScreen()
    } else if (uiState.errorMessage.isNotBlank()) {
        showErrorText(uiState.errorMessage)
    }
}

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    digits: Int = 4,
    otpValue: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(digits) { index ->
            val char = when {
                index >= otpValue.length -> ""
                else -> otpValue[index].toString()
            }
            val isFocussed = index == otpValue.length
            Text(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .border(
                        width = if (isFocussed) {
                            2.dp
                        } else {
                            1.dp
                        },
                        color = if (index <= otpValue.length) {
                            Color.Green
                        } else {
                            Color.LightGray
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .wrapContentHeight(Alignment.CenterVertically),
                text = char,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Green,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyEmailScreenPreview() {
    NutrakTheme {
        VerifyEmailScreen(
            emailId = "abc@xyz.com",
            resendOtp = { },
            validateOtp = { false },
            submitOtp = { },
            navigateToWelcomeScreen = { },
            showErrorText = { },
            uiState = AuthUiState()
        )
    }
}
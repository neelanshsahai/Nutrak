package com.example.nutrak.ui.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
): ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

//    Sample function structure for login
    fun login(/*Pass in the parameters*/) {
//         _uiState.update { AuthUiState(isLoading = true) }

//         Call AuthRepository which then calls a function from the AuthApi class

//         Disable loading on getting the response from the server
//         _uiState.update { AuthUiState() }

//         if (apiResponse.isSuccess) {
             _uiState.update { AuthUiState(isAuthSuccessful = true) }
//         } else {
//             _uiState.update { AuthUiState(isAuthSuccessful = false, errorMessage = apiResponse.errorMessage) }
//         }
    }

//    Sample function structure for sign up
    fun signUp(/*Pass in the parameters*/) {
//         _uiState.update { AuthUiState(isLoading = true) }

//         Call AuthRepository which then calls a function from the AuthApi class
//         authRepository.login(email, password)

//         Disable loading on getting the response from the server
//         _uiState.update { AuthUiState() }

//         if (apiResponse.isSuccess) {
            _uiState.update { AuthUiState(isAuthSuccessful = true) }
//         } else {
//             _uiState.update { AuthUiState(isAuthSuccessful = false, errorMessage = apiResponse.errorMessage) }
//         }
    }

    // Add implementation of SIWG
    fun signInWithGoogle() {

    }

    // Add implementation of SIWA
    fun signInWithApple() {

    }

    // Add OTP verification logic
    fun verifyLoginOtp() {
        _uiState.update { AuthUiState(isAuthSuccessful = true) }
    }

    // Add resend OTP logic
    fun resendOtp() {

    }

    // Add other OTP validation parameters
    fun isOtpValid(otpValue: String): Boolean {
        return otpValue.length == 4
    }

    // Add other checks if required for the email address
    fun isEmailValid(emailId: String): Boolean {
        return emailId.isNotBlank()
    }

    // Add other checks if required for the password
    fun isPasswordValid(password: String): Boolean {
        return password.isNotBlank()
    }

    // Add other checks if required for all the fields
    fun isFormDataValid(
        firstName: String,
        lastName: String,
        emailId: String,
        password: String,
    ): Boolean {
        return firstName.isNotBlank()
                && lastName.isNotBlank()
                && emailId.isNotBlank()
                && password.isNotBlank()
    }
}

data class AuthUiState(
    val isAuthSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)

package com.example.nutrak.ui.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutrak.utils.IS_FIRST_LAUNCH
import com.example.nutrak.utils.IS_USER_LOGGED_IN
import com.example.nutrak.utils.read
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
): ViewModel() {

    private val isFirstLaunchKey = booleanPreferencesKey(IS_FIRST_LAUNCH)
    private val isLoggedInKey = booleanPreferencesKey(IS_USER_LOGGED_IN)

    fun delayAndNavigateToNextScreen(
        onCompletion: (nextScreen: NextScreen) -> Unit,
    ) {
        viewModelScope.launch {
            val isAppOpenedForTheFirstTime = dataStore.read(isFirstLaunchKey) ?: true
            val isUserAlreadyLoggedIn = dataStore.read(isLoggedInKey) ?: false

            delay(2000L)

            onCompletion(if (isUserAlreadyLoggedIn) {
                // Navigate to Dashboard if the user is already logged in
                NextScreen.DASHBOARD_SCREEN
            } else if (!isAppOpenedForTheFirstTime) {
                // Navigate to Login Screen if the user is not logged but has already opened the app at least once
                NextScreen.LOGIN_SCREEN
            } else {
                // Navigate to the intro slider page if the user is new to the app and has opened it for the first time
                NextScreen.INTRO_SCREEN
            })

//            dataStore.edit { it[isFirstLaunchKey] = true }
        }
    }
}

enum class NextScreen {
    INTRO_SCREEN,
    LOGIN_SCREEN,
    DASHBOARD_SCREEN,
}
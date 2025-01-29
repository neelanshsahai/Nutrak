package com.example.nutrak.ui.navigation

import androidx.annotation.StringRes
import com.example.nutrak.R

enum class AppScreens(@StringRes val title: Int) {
    SplashScreen(R.string.splash_screen),
    IntroScreen(R.string.intro_screen),
    LoginScreen(R.string.login_screen),
    SignUpScreen(R.string.sign_up_screen),
    VerifyEmailScreen(R.string.verify_email_screen),
    PersonalDetailsScreen(R.string.personal_details_screen),
    DashboardScreen(R.string.dashboard_screen),
    CameraScanScreen(R.string.camera_scan_screen),
    ScanProgressScreen(R.string.scan_progress_screen),
    ScanResultsScreen(R.string.scan_results_screen),
    StreaksScreen(R.string.streaks_screen),
}
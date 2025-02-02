package com.example.nutrak.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutrak.ui.screens.IntroScreen
import com.example.nutrak.ui.screens.LoginScreen
import com.example.nutrak.ui.screens.PasswordScreen
import com.example.nutrak.ui.screens.PersonalDetailsScreen
import com.example.nutrak.ui.screens.SignUpScreen
import com.example.nutrak.ui.screens.SplashScreen
import com.example.nutrak.ui.screens.VerifyEmailScreen
import com.example.nutrak.ui.screens.WelcomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppScreens.SplashScreen.name,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = AppScreens.SplashScreen.name) {
            SplashScreen(
                viewModel = hiltViewModel(),
                navigateToIntro = { navController.navigate(AppScreens.IntroScreen.name) },
                navigateToLogin = { navController.navigate(AppScreens.LoginScreen.name) },
                navigateToDashboard = { navController.navigate(AppScreens.DashboardScreen.name) },
            )
        }

        composable(route = AppScreens.IntroScreen.name) {
            IntroScreen(
                navigateToLogin = { navController.navigate(AppScreens.LoginScreen.name) }
            )
        }

        composable(route = AppScreens.LoginScreen.name) {
            LoginScreen(
                viewModel = hiltViewModel(),
                navigateToSignUp = { navController.navigate(AppScreens.SignUpScreen.name) },
                navigateToPassword = { emailId ->
                    navController.navigate(AppScreens.PasswordScreen.name + "/$emailId")
                }
            )
        }

        composable(route = AppScreens.PasswordScreen.name + "/{emailId}") {
            val email = it.arguments?.getString("emailId") ?: ""
            PasswordScreen(
                viewModel = hiltViewModel(),
                emailId = email,
                navigateToSignUp = { navController.navigate(AppScreens.SignUpScreen.name) },
                navigateToDashboard = { navController.navigate(AppScreens.DashboardScreen.name) }
            )
        }

        composable(route = AppScreens.SignUpScreen.name) {
            SignUpScreen(
                viewModel = hiltViewModel(),
                navigateToSignInScreen = { navController.navigate(AppScreens.LoginScreen.name) },
                navigateToVerifyScreen = { emailId ->
                    navController.navigate(AppScreens.VerifyEmailScreen.name + "/$emailId")
                }
            )
        }

        composable(route = AppScreens.VerifyEmailScreen.name + "/{emailId}") {
            val email = it.arguments?.getString("emailId") ?: ""
            VerifyEmailScreen(
                emailId = email,
                viewModel = hiltViewModel(),
                navigateToWelcomeScreen = { navController.navigate(AppScreens.WelcomeScreen.name) }
            )
        }

        composable(route = AppScreens.WelcomeScreen.name) {
            WelcomeScreen(
                navigateToPersonalDetails = { navController.navigate(AppScreens.PersonalDetailsScreen.name) }
            )
        }

        composable(route = AppScreens.PersonalDetailsScreen.name) {
            PersonalDetailsScreen(
                viewModel = hiltViewModel(),
                navigateToDashboard = { navController.navigate(AppScreens.DashboardScreen.name) }
            )
        }

        composable(route = AppScreens.DashboardScreen.name) {

        }
    }
}
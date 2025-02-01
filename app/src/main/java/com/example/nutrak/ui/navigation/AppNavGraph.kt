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
import com.example.nutrak.ui.screens.SplashScreen

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
                navigateToSignUp = { navController.navigate(AppScreens.SignUpScreen.name) },
                navigateToPassword = { emailId ->
                    navController.navigate(AppScreens.PasswordScreen.name + "/$emailId")
                }
            )
        }

        composable(route = AppScreens.PasswordScreen.name + "/{emailId}") {
            val email = it.arguments?.getString("emailId") ?: ""
            PasswordScreen(
                emailId = email,
                navigateToSignUp = { navController.navigate(AppScreens.SignUpScreen.name) },
                navigateToDashboard = { navController.navigate(AppScreens.DashboardScreen.name) }
            )
        }

        composable(route = AppScreens.SignUpScreen.name) {

        }

        composable(route = AppScreens.DashboardScreen.name) {

        }
    }
}
package com.example.nutrak.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                navigateToIntro = { navController.navigate(AppScreens.IntroScreen.name) },
                navigateToLogin = { navController.navigate(AppScreens.LoginScreen.name) },
                navigateToDashboard = { navController.navigate(AppScreens.DashboardScreen.name) },
            )
        }

        composable(route = AppScreens.IntroScreen.name) {

        }

        composable(route = AppScreens.LoginScreen.name) {

        }

        composable(route = AppScreens.DashboardScreen.name) {

        }
    }
}
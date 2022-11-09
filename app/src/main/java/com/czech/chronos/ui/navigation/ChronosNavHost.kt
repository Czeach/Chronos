package com.czech.chronos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.czech.chronos.ui.convert.ConvertScreen
import com.czech.chronos.ui.home.HomeScreen
import com.czech.chronos.ui.search.SearchScreen
import com.czech.chronos.ui.settings.SettingsScreen
import com.czech.chronos.ui.splash.SplashScreen


@Composable
fun ChronosNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route,
        modifier = modifier
    ) {

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(
                modifier = modifier
            ) {
                navController.navigate(Screens.HomeScreen.route) {
                    popUpTo(Screens.SplashScreen.route) { inclusive = true }
                }
            }
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(
                onSettingsClicked = { navController.navigate(Screens.SettingsScreen.route) },
                onFABClicked = { navController.navigate(Screens.SearchScreen.route) },
                onConvertClicked = { navController.navigate(Screens.ConvertScreen.route) })
        }
        composable(route = Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screens.ConvertScreen.route) {
            ConvertScreen(navController = navController)
        }
        composable(Screens.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
    }
}
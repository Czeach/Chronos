package com.czech.chronos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.czech.chronos.ui.convert.ConvertScreen
import com.czech.chronos.ui.home.HomeScreen
import com.czech.chronos.ui.search.SearchScreen
import com.czech.chronos.ui.settings.SettingsScreen
import com.czech.chronos.ui.splash.SplashScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
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
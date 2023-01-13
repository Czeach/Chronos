package com.czech.chronos.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.czech.chronos.ui.convert.ConvertScreen
import com.czech.chronos.ui.home.HomeScreen
import com.czech.chronos.ui.home.HomeViewModel
import com.czech.chronos.ui.search.SearchScreen
import com.czech.chronos.ui.search.SearchViewModel
import com.czech.chronos.ui.settings.SettingsScreen
import com.czech.chronos.ui.splash.SplashScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChronosNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {

        fun onBackPressed() {
            if (navController.previousBackStackEntry != null) navController.navigateUp()
        }

        composable(route = Screens.SplashScreen.route) {
            SplashScreen {
                navController.navigate(Screens.HomeScreen.route) {
                    popUpTo(Screens.SplashScreen.route) { inclusive = true }
                }
            }
        }
        composable(route = Screens.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                onSettingsClicked = { navController.navigate(Screens.SettingsScreen.route) },
                onFABClicked = { navController.navigate(Screens.SearchScreen.route) },
                onConvertClicked = { navController.navigate(Screens.ConvertScreen.route) },
                viewModel = viewModel
            )
        }
        composable(route = Screens.SearchScreen.route) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                onBackPressed = { onBackPressed() },
                viewModel = viewModel
            )
        }
        composable(route = Screens.ConvertScreen.route) {
            ConvertScreen(navController = navController)
        }
        composable(Screens.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
    }
}
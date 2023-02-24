package com.czech.chronos.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.czech.chronos.ui.screens.ConvertScreen
import com.czech.chronos.ui.screens.home.HomeScreen
import com.czech.chronos.ui.screens.search.SearchScreen
import com.czech.chronos.ui.screens.search.SearchViewModel
import com.czech.chronos.ui.settings.SettingsScreen
import com.czech.chronos.ui.splash.SplashScreen
import com.czech.chronos.ui.screens.HomeViewModel


@SuppressLint("UnrememberedGetBackStackEntry")
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
                onConvertClicked = {
                    navController.navigate(Screens.ConvertScreen.route)
               },
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
        composable(route = Screens.ConvertScreen.route) { backStackEntry ->

            val homeEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screens.HomeScreen.route)
            }

            val viewModel = hiltViewModel<HomeViewModel>(homeEntry)
            ConvertScreen(
                onBackPressed = { onBackPressed() },
                viewModel = viewModel
            )
        }
        composable(Screens.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
    }
}
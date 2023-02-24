package com.czech.chronos.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.czech.chronos.ui.screens.ConvertScreen
import com.czech.chronos.ui.screens.ConvertViewModel
import com.czech.chronos.ui.screens.home.HomeScreen
import com.czech.chronos.ui.screens.search.SearchScreen
import com.czech.chronos.ui.screens.search.SearchViewModel
import com.czech.chronos.ui.settings.SettingsScreen
import com.czech.chronos.ui.splash.SplashScreen
import com.czech.chronos.ui.screens.home.HomeViewModel


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
            val homeEntry = remember {
                navController.getBackStackEntry(Screens.HomeScreen.route)
            }
            val viewModel = hiltViewModel<HomeViewModel>(homeEntry)
            HomeScreen(
                onSettingsClicked = { navController.navigate(Screens.SettingsScreen.route) },
                onFABClicked = { navController.navigate(Screens.SearchScreen.route) },
                onConvertClicked = { date_time, gmt_offset ->
                    navController.navigate(Screens.ConvertScreen.route + "/$date_time" + "/$gmt_offset")
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
        composable(route = Screens.ConvertScreen.route + "/{date_time}" + "/{gmt_offset}",
            arguments = listOf(
                navArgument("date_time") {
                    type = NavType.StringType
                },
                navArgument("gmt_offset") {
                    type = NavType.IntType
                }
            )
        ) {

            val convertEntry = remember {
                navController.getBackStackEntry(Screens.ConvertScreen.route + "/{date_time}" + "/{gmt_offset}")
            }
            val viewModel = hiltViewModel<ConvertViewModel>(convertEntry)
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
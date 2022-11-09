package com.czech.chronos.ui.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("SplashScreen")
    object HomeScreen: Screens("HomeScreen")
    object SearchScreen: Screens("SearchScreen")
    object ConvertScreen: Screens("ConvertScreen")
    object SettingsScreen: Screens("Settings Screen")
}


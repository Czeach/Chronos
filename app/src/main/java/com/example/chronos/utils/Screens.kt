package com.example.chronos.utils

sealed class Screens(val route: String) {
    object SplashScreen : Screens("SplashScreen")
    object HomeScreen: Screens("HomeScreen")
    object SearchScreen: Screens("SearchScreen")
    object ConvertScreen: Screens("ConvertScreen")
    object SettingsScreen: Screens("Settings Screen")
}


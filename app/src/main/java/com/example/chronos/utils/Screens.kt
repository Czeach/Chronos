package com.example.chronos.utils

sealed class Screens(val route: String) {
    object SplashScreen : Screens("SplashScreen")
    object HomeScreen: Screens("HomeScreen")
}

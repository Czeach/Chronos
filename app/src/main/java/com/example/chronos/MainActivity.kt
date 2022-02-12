package com.example.chronos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chronos.ui.theme.ChronosTheme
import com.example.chronos.uis.convert.ConvertScreen
import com.example.chronos.uis.home.HomeScreen
import com.example.chronos.uis.search.SearchScreen
import com.example.chronos.uis.settings.SettingsScreen
import com.example.chronos.uis.splash.SplashScreen
import com.example.chronos.utils.Screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChronosTheme {

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
        }
    }
}

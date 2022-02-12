package com.example.chronos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chronos.theme.ChronosTheme
import com.example.chronos.presentation.convert.ConvertScreen
import com.example.chronos.presentation.home.HomeScreen
import com.example.chronos.presentation.navigation.Navigation
import com.example.chronos.presentation.search.SearchScreen
import com.example.chronos.presentation.settings.SettingsScreen
import com.example.chronos.presentation.splash.SplashScreen
import com.example.chronos.presentation.navigation.Screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChronosTheme {
                Navigation()
            }
        }
    }
}

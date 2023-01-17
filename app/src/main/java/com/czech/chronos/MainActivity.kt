package com.czech.chronos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.czech.chronos.ui.theme.ChronosTheme
import com.czech.chronos.ui.navigation.ChronosNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChronosApp()
        }
    }
}

@Composable
fun ChronosApp() {
    ChronosTheme {
        val navController = rememberNavController()
        ChronosNavHost(
            navController = navController
        )
    }

}

package com.czech.chronos

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.czech.chronos.ui.theme.ChronosTheme
import com.czech.chronos.ui.navigation.ChronosNavHost
import dagger.hilt.android.AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChronosApp()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChronosApp() {
    ChronosTheme {
        val navController = rememberNavController()
        ChronosNavHost(
            navController = navController
        )
    }

}

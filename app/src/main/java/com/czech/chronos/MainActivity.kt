package com.czech.chronos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.czech.chronos.theme.ChronosTheme
import com.czech.chronos.ui.navigation.ChronosNavHost

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
            navController = navController,
            modifier = Modifier.padding(0.dp)
        )
    }

}

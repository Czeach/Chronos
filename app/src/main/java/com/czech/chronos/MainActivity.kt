package com.czech.chronos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.czech.chronos.theme.ChronosTheme
import com.czech.chronos.presentation.navigation.Navigation

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

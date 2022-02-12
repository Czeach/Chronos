package com.example.chronos.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chronos.R
import com.example.chronos.utils.Fonts
import com.example.chronos.presentation.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(Screens.HomeScreen.route) {
            popUpTo(Screens.SplashScreen.route) { inclusive = true }
        }
    }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.chronos_image),
                    contentDescription = "chronos image",
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .size(120.dp, 120.dp)
                )
            Spacer(
                modifier = Modifier.height(25.dp)
            )
                Text(
                    text = "Time is of the Essence",
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Fonts.lexendDeca,
                    fontWeight = FontWeight.W400,
                )
            }
        }

    }

}
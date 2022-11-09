package com.czech.chronos.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.czech.chronos.R
import com.czech.chronos.utils.Fonts
import com.czech.chronos.presentation.navigation.Screens
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
        color = MaterialTheme.colorScheme.background,
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
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Fonts.lexendDeca,
                    fontWeight = FontWeight.W400,
                )
            }
        }

    }

}
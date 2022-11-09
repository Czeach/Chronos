package com.czech.chronos.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.czech.chronos.R
import com.czech.chronos.utils.Fonts
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onTimeOut: () -> Unit
) {

    LaunchedEffect(true) {
        delay(3000)
        onTimeOut()
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
                    painter = painterResource(
                        id = if (isSystemInDarkTheme()) R.drawable.chronos_image_red else R.drawable.chronos_image_purple),
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
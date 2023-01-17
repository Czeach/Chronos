package com.czech.chronos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.czech.chronos.R
import com.czech.chronos.utils.Fonts

@Composable
fun EmptyListItem() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = if (isSystemInDarkTheme()) R.drawable.search_img_dark else R.drawable.search_img_light),
            contentDescription = "search_image",
            modifier = Modifier
        )
        Spacer(
            modifier = Modifier
                .height(24.dp)
        )
        Text(
            text = "Search for a city",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            fontFamily = Fonts.lexendDeca,
            fontWeight = FontWeight.W400,
        )
    }
}
package com.czech.chronos.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.czech.chronos.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: @Composable () -> Unit,
    actions: @Composable () -> Unit = {},
    onBackPressed: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface),
        title = { title() },
        navigationIcon = {
            IconButton(
                onClick = { onBackPressed() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "back_button"
                )
            }
        },
        actions = { actions() }
    )
}
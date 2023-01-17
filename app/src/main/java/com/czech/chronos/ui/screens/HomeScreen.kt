package com.czech.chronos.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.czech.chronos.R
import com.czech.chronos.ui.components.HomeFeatures
import com.czech.chronos.ui.viewModels.HomeViewModel
import com.czech.chronos.utils.Fonts

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    onSettingsClicked: () -> Unit,
    onFABClicked: () -> Unit,
    onConvertClicked: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val getTime = viewModel.getTime.collectAsState()
    val getDate = viewModel.getDate.collectAsState()

    HomeFeatures(
        timeText = getTime.value,
        dateText = getDate.value,
        onSettingsClicked = { onSettingsClicked() },
        onFABClicked = { onFABClicked() },
        onConvertClicked = { onConvertClicked() }
    )
}


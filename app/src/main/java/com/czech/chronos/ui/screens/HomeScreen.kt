package com.czech.chronos.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.czech.chronos.utils.DateUtil
import com.czech.chronos.utils.Fonts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    onSettingsClicked: () -> Unit,
    onFABClicked: () -> Unit,
    onConvertClicked: () -> Unit,
    viewModel: HomeViewModel
) {

    val timeFormatter = remember { DateUtil.timeFormat }
    val dateFormatter = remember { DateUtil.shortDateFormat }

    var time by remember {
        mutableStateOf(
            LocalDateTime.now().format(timeFormatter)
        )
    }
    var date by remember {
        mutableStateOf(
            LocalDateTime.now().format(dateFormatter)
        )
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            while (true) {
                time = LocalDateTime.now().format(timeFormatter)
                date = LocalDateTime.now().format(dateFormatter)
                delay(1000L)
            }
        }
    }

    viewModel.getSavedLocationsFromDB()

    Scaffold { padding ->
        HomeFeatures(
            timeText = time,
            dateText = date,
            onSettingsClicked = { onSettingsClicked() },
            onFABClicked = { onFABClicked() },
            onConvertClicked = { onConvertClicked() },
            locations = viewModel.savedLocations.collectAsState().value,
            modifier = Modifier
                .padding(padding)
        )
    }




}


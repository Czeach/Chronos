package com.czech.chronos.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.czech.chronos.ui.components.ConvertBottomSheetContent
import com.czech.chronos.ui.components.HomeFeatures
import com.czech.chronos.ui.viewModels.HomeViewModel
import com.czech.chronos.utils.DateUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    onSettingsClicked: () -> Unit,
    onFABClicked: () -> Unit,
    onConvertClicked: () -> Unit,
    viewModel: HomeViewModel
) {

    val context = LocalContext.current

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded},
        skipHalfExpanded = true
    )

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContent = {
            ConvertBottomSheetContent(
                context = context,
                modifier = Modifier
                    .defaultMinSize(minHeight = 1.dp)
            )
        }
    ) {
        Scaffold { padding ->

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

            HomeFeatures(
                timeText = time,
                dateText = date,
                onSettingsClicked = { onSettingsClicked() },
                onFABClicked = { onFABClicked() },
                openBottomSheet = {
                    coroutineScope.launch {
                        if (bottomSheetState.isVisible)
                            bottomSheetState.hide()
                        else
                            bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                    }
                },
                locations = viewModel.savedLocations.collectAsState().value,
                modifier = Modifier
                    .padding(padding)
            )
        }
    }
}


package com.czech.chronos.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.czech.chronos.ui.components.ConvertBottomSheetContent
import com.czech.chronos.ui.components.HomeFeatures
import com.czech.chronos.ui.screens.home.HomeViewModel
import com.czech.chronos.utils.DateUtil
import com.czech.chronos.utils.states.HomePredictionsState
import com.czech.chronos.utils.states.TargetPredictionsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

@SuppressLint("StateFlowValueCalledInComposition")
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

    val time = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val date = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val homeInput = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val targetInput = remember {
        mutableStateOf(TextFieldValue(""))
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContent = {
            ConvertBottomSheetContent(
                time = time,
                date = date,
                homeInput = homeInput,
                targetInput = targetInput,
                context = context,
                homePredictions = viewModel.homePredictionsList.value,
                targetPredictions = viewModel.targetPredictionsList.value,
                onHomePredictionClick = { location ->
                    homeInput.value = TextFieldValue(location)
                    viewModel.homePredictionsList.value = emptyList()
                    viewModel.homePredictionsState.value = null
                },
                onTargetPredictionClick = { location ->
                    targetInput.value = TextFieldValue(location)
                    viewModel.targetPredictionsList.value = emptyList()
                    viewModel.targetPredictionsState.value = null
                },
                modifier = Modifier
                    .defaultMinSize(minHeight = 1.dp)
            )
        }
    ) {
        Scaffold { padding ->

            val timeFormatter = remember { DateUtil.timeFormat }
            val dateFormatter = remember { DateUtil.shortDateFormat }

            var localTime by remember {
                mutableStateOf(
                    LocalDateTime.now().format(timeFormatter)
                )
            }
            var localDate by remember {
                mutableStateOf(
                    LocalDateTime.now().format(dateFormatter)
                )
            }

            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    while (true) {
                        localTime = LocalDateTime.now().format(timeFormatter)
                        localDate = LocalDateTime.now().format(dateFormatter)
                        delay(1000L)
                    }
                }
            }

            viewModel.getSavedLocationsFromDB()

            if (homeInput.value.text.isNotEmpty() && homeInput.value.text.length > 2) {
                LaunchedEffect(key1 = Unit) {
                    delay(200)

                    viewModel.getHomePredictions(homeInput.value.text)
                }

            }

            if (targetInput.value.text.isNotEmpty() && targetInput.value.text.length >2) {
                LaunchedEffect(key1 = Unit) {
                    delay(200)

                    viewModel.getTargetPredictions(targetInput.value.text)
                }
            }
            ObservePredictions(
                viewModel = viewModel
            )

            HomeFeatures(
                timeText = localTime,
                dateText = localDate,
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ObservePredictions(
    viewModel: HomeViewModel
) {
    when (val state = viewModel.homePredictionsState.collectAsState().value) {
        is HomePredictionsState.Loading -> {

        }
        is HomePredictionsState.Success -> {
            if (state.data != null) {
                viewModel.homePredictionsList.value = state.data
            }

        }
        else -> {

        }
    }

    when (val state = viewModel.targetPredictionsState.collectAsState().value) {
        is TargetPredictionsState.Loading -> {

        }
        is TargetPredictionsState.Success -> {
            if (state.data != null) {
                viewModel.targetPredictionsList.value = state.data
            }

        }
        else -> {

        }
    }
}


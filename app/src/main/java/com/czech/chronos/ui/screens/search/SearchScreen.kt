package com.czech.chronos.ui.screens.search

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.czech.chronos.ui.components.*
import com.czech.chronos.utils.states.CurrentTimeState
import com.czech.chronos.utils.states.PredictionsState
import com.czech.chronos.utils.toCurrentTimeEntity
import kotlinx.coroutines.*


@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBackPressed: () -> Unit,
    viewModel: SearchViewModel
) {

    var hideKeyboard by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppBar(
                title = {
                    SearchBar(
                        input = viewModel.inputState,
                        hint = "Search...",
                        hideKeyboard = hideKeyboard,
                        resetCurrentTime = {
                            viewModel.currentTimeState.value = null
                        },
                        onFocusClear = { hideKeyboard = false },
                        modifier = Modifier
                    )
                },
                onBackPressed = { onBackPressed() }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .padding(top = 12.dp)
        ) {
            if (viewModel.inputState.value.text.isNotEmpty()) {
                viewModel.currentTimeFromDB.value = listOf()
                if (viewModel.inputState.value.text.length > 2) {
                    LaunchedEffect(key1 = viewModel.predictionsState.value) {

                        delay(500)

                        viewModel.getCityPredictions(viewModel.inputState.value.text)
                    }
                    ObserveCityPredictions(
                        viewModel = viewModel
                    )
                    ObserveCurrentTime(
                        viewModel = viewModel
                    )
                }
            } else {
                viewModel.getCurrentTimeListFromDB()
                viewModel.currentTimeFromDB.collectAsState().value
            }
            if (viewModel.currentTimeFromDB.value.isNotEmpty()) {
                SearchResultList(
                    list = viewModel.currentTimeFromDB.collectAsState().value,
                    onCheckedChange = { checked, item ->
                        if (!checked) viewModel.deleteCurrentTimeFromDB(item.requestedLocation.toString())
                    }
                )
            } else {
                viewModel.predictionsState.value = null
                EmptyListItem()
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ObserveCityPredictions(
    viewModel: SearchViewModel
) {
    when (val state = viewModel.predictionsState.collectAsState().value) {
        is PredictionsState.Loading -> {

        }
        is PredictionsState.Success -> {
            PredictionsResultList(
                state.data,
                onItemClick = {
                    viewModel.getCurrentTime(it)
                    viewModel.predictionsState.value = null
                }
            )
        }
        else -> {

        }
    }
}

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ObserveCurrentTime(
    viewModel: SearchViewModel
) {
    when (val state = viewModel.currentTimeState.collectAsState().value) {
        is CurrentTimeState.Loading -> {

        }
        is CurrentTimeState.Success -> {
            viewModel.isCurrentTimeInDB(state.data?.requestedLocation.toString())

            var checkedState: Boolean by mutableStateOf(viewModel.isInDB.collectAsState().value)

            if (state.data != null) {
                SearchResultItem(
                    data = state.data,
                    checked = checkedState,
                    onCheckedChange = { newValue ->
                        checkedState = newValue
                        when (checkedState) {
                            true -> {
                                viewModel.insertCurrentTimeIntoDB(state.data.toCurrentTimeEntity(), checkedState)
                                viewModel.inputState.value = TextFieldValue("")
                            }
                            false -> {
                                viewModel.deleteCurrentTimeFromDB(state.data.requestedLocation.toString())
                            }
                        }
                    }
                )
            }
        }
        is CurrentTimeState.Error -> {

        }
        else -> {
        }
    }
}
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
import com.czech.chronos.data.states.CurrentTimeState
import com.czech.chronos.data.states.PredictionsState
import com.czech.chronos.utils.toCurrentTimeEntity
import kotlinx.coroutines.*


@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBackPressed: () -> Unit,
    viewModel: SearchViewModel
) {

    var hideKeyboard by remember { mutableStateOf(false) }
    val input = mutableStateOf(TextFieldValue(""))

    Scaffold(
        topBar = {
            AppBar(
                title = {
                    SearchBar(
                        input = input,
                        hint = "Search...",
                        hideKeyboard = hideKeyboard,
                        resetCurrentTime = {
//                            viewModel.currentTimeState.value = null
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


            if (input.value.text.isNotEmpty()) {
                viewModel.currentTimeFromDB.value = listOf()
                if (input.value.text.length > 2) {
                    LaunchedEffect(key1 = viewModel.predictionsState.value) {

                        delay(500)

                        viewModel.getCityPredictions(input.value.text)
                    }
                    ObserveCityPredictions(
                        viewModel = viewModel
                    )
                    ObserveCurrentTime(
                        viewModel = viewModel,
                        input = input
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
//                        if (!checked) viewModel.deleteCurrentTimeFromDB(item.requestedLocation.toString())
                    }
                )
            } else {
                viewModel.predictionsState.value = null
                EmptyListItem()
            }
        }

    }
}

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
@Composable
fun ObserveCurrentTime(
    viewModel: SearchViewModel,
    input: MutableState<TextFieldValue>
) {
    when (val state = viewModel.currentTimeState.collectAsState().value) {
        is CurrentTimeState.Success -> {
//            viewModel.isCurrentTimeInDB(state.data?.requestedLocation.toString())

            var checkedState: Boolean by mutableStateOf(viewModel.isInDB.collectAsState().value)

            if (state.data != null) {
                SearchResultItem(
                    data = state.data,
                    checked = checkedState,
                    onCheckedChange = { newValue ->
                        checkedState = newValue
                        when (checkedState) {
                            true -> {
//                                viewModel.insertCurrentTimeIntoDB(state.data.toCurrentTimeEntity(), checkedState)
                                input.value = TextFieldValue("")
                            }
                            false -> {
//                                viewModel.deleteCurrentTimeFromDB(state.data.requestedLocation.toString())
                            }
                        }
                    }
                )
            }
        }
        else -> {
        }
    }
}
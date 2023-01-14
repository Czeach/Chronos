package com.czech.chronos.ui.search

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.czech.chronos.R
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.utils.AppBar
import com.czech.chronos.utils.Fonts
import com.czech.chronos.utils.states.CurrentTimeState
import com.czech.chronos.utils.states.PredictionsState
import com.czech.chronos.utils.toCurrentTimeEntity
import kotlinx.coroutines.delay


@SuppressLint("StateFlowValueCalledInComposition")
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
            }
            if (viewModel.currentTimeFromDB.value.isNotEmpty()) {
                SearchResultList(
                    list = viewModel.currentTimeFromDB.collectAsState().value,
                    viewModel = viewModel
                )
            } else {
                viewModel.predictionsState.value = null
                EmptyListState()
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

            viewModel.updateTimeFromServer(state.data?.timezoneLocation.toString())
            SearchResultItem(
                city = state.data?.requestedLocation.toString(),
                cityTime = viewModel.timeState.collectAsState().value,
                checked = checkedState,
                onCheckedChange = { newValue ->
                    checkedState = newValue
                    when (checkedState) {
                        true -> {
                            viewModel.insertCurrentTimeIntoDB(state.data?.toCurrentTimeEntity()!!, checkedState)
                            viewModel.inputState.value = TextFieldValue("")
                        }
                        false -> {
                            viewModel.deleteCurrentTimeFromDB(state.data?.requestedLocation.toString())
                        }
                    }
                }
            )
        }
        is CurrentTimeState.Error -> {

        }
        else -> {
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    input: MutableState<TextFieldValue>,
    onFocusClear: () -> Unit = {},
    resetCurrentTime: () -> Unit,
    hideKeyboard: Boolean = false,
    hint: String,
    modifier: Modifier
) {

    var isHintDisplayed by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current

    TextField(
        value = input.value,
        onValueChange = {
            input.value = it
            resetCurrentTime()
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 18.dp)
            .onFocusChanged {
                isHintDisplayed = it.hasFocus != true
            },
        placeholder = {
            Text(
                text = hint
            )
        },
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            fontFamily = Fonts.lexendDeca,
            fontWeight = FontWeight.W400
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.cancel_btn),
                contentDescription = "clear_text",
                modifier = modifier
                    .clickable {
                        input.value = TextFieldValue("")
                    }
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )

    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Composable
fun PredictionsResultList(
    list: List<PlacePredictions.Prediction?>?,
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(
            items = list!!
        ) { predictions ->
            PredictionsResultItem(
                city = predictions?.description.toString(),
                onItemClick = {
                    onItemClick(predictions?.description.toString())
                }
            )
        }
    }
}

@Composable
fun PredictionsResultItem(
    city: String,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp)
            .clickable {
                onItemClick()
            },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = city,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            fontFamily = Fonts.exo,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .padding(start = 22.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchResultList(
    list: List<CurrentTime>,
    viewModel: SearchViewModel
) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(
            items = list,
            key = { it.requestedLocation.toString() }
        ) { data ->
            viewModel.updateTimeFromServer(data.timezoneLocation.toString())
            SearchResultItem(
                city = data.requestedLocation.toString(),
                cityTime = viewModel.timeState.collectAsState().value,
                checked = data.checked,
                onCheckedChange = { checked ->
                    if (!checked) viewModel.deleteCurrentTimeFromDB(data.requestedLocation.toString())
                }
            )
        }
    }
}

@Composable
fun SearchResultItem(
    city: String,
    cityTime: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Checkbox(
                modifier = Modifier
                    .padding(start = 6.dp),
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.outlineVariant,
                    uncheckedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = MaterialTheme.colorScheme.scrim
                )
            )
            Text(
                text = city,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontFamily = Fonts.exo,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)

            )
            Text(
                text = cityTime,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontFamily = Fonts.lexendDeca,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(end = 14.dp)
            )
        }
    }
}

@Composable
fun EmptyListState() {
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

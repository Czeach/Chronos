package com.czech.chronos.ui.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.czech.chronos.utils.Fonts
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.czech.chronos.R
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.utils.AppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBackPressed: () -> Unit
) {
    val inputState = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(
        topBar = {
            AppBar(
                title = {
                        SearchBar(
                            input = inputState,
                        )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.cancel_btn),
                            contentDescription = "back_button"
                        )
                    }
                },
                onBackPressed = { onBackPressed() }
            )
        }
    ) { padding ->
        EmptyListState(
            padding = padding
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    input: MutableState<TextFieldValue>
) {
    TextField(
        value = input.value,
        onValueChange = {
            input.value = it
        },
        placeholder = {
            Text(
                text = "Search..."
            )
        },
        singleLine = true,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            fontFamily = Fonts.lexendDeca,
            fontWeight = FontWeight.W400
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
    )
}

@Composable
fun SearchResultList(
    list: List<CurrentTime>,
) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(
            items = list
        ) { data ->
            SearchResultItem(
                city = data.requestedLocation.toString(),
                cityTime = data.datetime.toString(),
                checked = false,
                onCheckedChange = {}
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
fun EmptyListState(padding: PaddingValues) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
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

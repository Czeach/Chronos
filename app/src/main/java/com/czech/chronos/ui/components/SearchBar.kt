package com.czech.chronos.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
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
import com.czech.chronos.utils.Fonts

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
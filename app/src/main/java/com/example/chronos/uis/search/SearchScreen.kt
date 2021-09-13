package com.example.chronos.uis.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.chronos.R
import com.example.chronos.utils.Fonts
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField

@Composable
fun SearchScreen(navController: NavController) {

    val constraints = ConstraintSet {
        val searchColumn = createRefFor("search_column")
        val backBtn = createRefFor("back")
        val searchBar = createRefFor("search_bar")
        val closeBtn = createRefFor("close")
        val searchIcon = createRefFor("search_icon")
        val searchCityText = createRefFor("search_city")

        constrain(searchColumn) {
            top.linkTo(parent.top, margin = 2.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.value(64.dp)
        }
        constrain(backBtn) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start, margin = 24.dp)
        }
        constrain(searchBar) {
            top.linkTo(backBtn.top)
            bottom.linkTo(backBtn.bottom)
            start.linkTo(backBtn.start, margin = 24.dp)
        }
    }

    ConstraintLayout(constraintSet = constraints, modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)) {

        ConstraintLayout(modifier = Modifier
            .layoutId("search_column")
            .background(color = MaterialTheme.colors.onBackground)) {

            Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "back button",
            modifier = Modifier
                .layoutId("back")
                .clickable(enabled = true) {

                }
            )
            SearchBar(
                modifier = Modifier.layoutId("search_bar"),
                hint = "Search..."
            )

        }

    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier,
              hint: String = "",
              onSearch: (String) -> Unit = {}
) {
    var searchInput by remember {mutableStateOf("")}

    var isHintDisplayed by remember { mutableStateOf(hint != "")}
    
    Box(modifier = modifier) {

        BasicTextField(
            value = searchInput,
            onValueChange = { value ->
                searchInput = value
                onSearch(value)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = Fonts.lexendDeca,
                fontWeight = FontWeight.W400),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp)
//                .onFocusChanged {
//                    isHintDisplayed = it != FocusState.isFocused
//                }
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = MaterialTheme.colors.primary)
        }
    }
}

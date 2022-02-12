package com.example.chronos.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SearchScreen(navController: NavController) {

    val constraints = ConstraintSet {
        val searchColumn = createRefFor("search_column")
        val backBtn = createRefFor("back")
        val searchBar = createRefFor("search_bar")
        val searchImageHolder = createRefFor("search_image_holder")
        val searchImage = createRefFor("search_image")
        val searchCityText = createRefFor("search_city")

        constrain(searchColumn) {
            top.linkTo(parent.top, margin = 2.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.value(48.dp)
        }
        constrain(backBtn) {
            top.linkTo(searchColumn.top)
            bottom.linkTo(searchColumn.bottom)
            start.linkTo(searchColumn.start, margin = 24.dp)
        }
        constrain(searchBar) {
            top.linkTo(backBtn.top)
            bottom.linkTo(backBtn.bottom)
            start.linkTo(backBtn.end, margin = 16.dp)
        }
        constrain(searchImageHolder) {
            top.linkTo(searchColumn.bottom)
            bottom.linkTo(parent.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
        constrain(searchImage) {
            top.linkTo(searchImageHolder.top)
            start.linkTo(searchImageHolder.start)
            end.linkTo(searchImageHolder.end)
        }
        constrain(searchCityText) {
            top.linkTo(searchImage.bottom, margin = 10.dp)
            start.linkTo(searchImage.start)
            end.linkTo(searchImage.end)
        }
    }

    ConstraintLayout(constraintSet = constraints, modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)) {

        ConstraintLayout(modifier = Modifier
            .layoutId("search_column")
            .background(color = MaterialTheme.colors.onBackground)) {}

            Image(painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "back button",
                modifier = Modifier
                    .layoutId("back")
                    .clickable(enabled = true) {

                    }
            )

        SearchBar(
            hint = "Search Timezones...",
            modifier = Modifier.layoutId("search_bar")
        ) {

        }

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .layoutId("search_image_holder")){}

            Image(painter = painterResource(id = R.drawable.search_image),
                contentDescription = "back button",
                modifier = Modifier
                    .layoutId("search_image")
            )

            Text(
                text = "Search for a City",
                color = MaterialTheme.colors.primary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = Fonts.lexendDeca,
                fontWeight = FontWeight.W400,
                modifier = Modifier.layoutId("search_city")
            )

    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier,
              hint: String = "",
              onSearch: (String) -> Unit = {}
) {
    var searchInput by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    
    Box(modifier = modifier) {

        BasicTextField(
            value = searchInput,
            onValueChange = {
                searchInput = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                color = MaterialTheme.colors.primary,
                fontSize = 16.sp,
                fontFamily = Fonts.lexendDeca,
                fontWeight = FontWeight.W400),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused


                }
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

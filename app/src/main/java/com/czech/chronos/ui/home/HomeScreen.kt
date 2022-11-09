package com.czech.chronos.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.czech.chronos.R
import com.czech.chronos.utils.Fonts

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSettingsClicked: () -> Unit,
    onFABClicked: () -> Unit,
    onConvertClicked: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {

    val getTime = viewModel.getTime.collectAsState()
    val getDate = viewModel.getDate.collectAsState()

    HomeFeatures(
        timeText = getTime.value,
        dateText = getDate.value,
        onSettingsClicked = { onSettingsClicked() },
        onFABClicked = { onFABClicked() },
        onConvertClicked = { onConvertClicked() },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun HomeFeatures(
    timeText: String,
    dateText: String,
    onSettingsClicked: () -> Unit,
    onFABClicked: () -> Unit,
    onConvertClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val constrains = ConstraintSet {
        val settingsBtnView = createRefFor("settings")
        val clockTextView = createRefFor("clock_text")
        val timeView = createRefFor("time")
        val dateView = createRefFor("date")
        val lineView = createRefFor("line")
        val fabBtnView = createRefFor("fab")
        val convertTimeBarView = createRefFor("convert_time_bar")

        constrain(settingsBtnView) {
            top.linkTo(parent.top, margin = 20.dp)
            end.linkTo(parent.end, margin = 20.dp)
            width = Dimension.value(20.dp)
            height = Dimension.value(20.dp)
        }
        constrain(clockTextView) {
            top.linkTo(settingsBtnView.bottom, margin = 6.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(timeView) {
            top.linkTo(clockTextView.bottom, margin = 18.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(dateView) {
            top.linkTo(timeView.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(lineView) {
            top.linkTo(dateView.bottom, margin = 50.dp)
            start.linkTo(parent.start, margin = 30.dp)
            end.linkTo(parent.end, margin = 30.dp)
            width = Dimension.fillToConstraints
            height = Dimension.value(1.dp)
        }
        constrain(fabBtnView) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(convertTimeBarView.top, margin = 48.dp)
            height = Dimension.value(56.dp)
            width = Dimension.value(56.dp)
        }
        constrain(convertTimeBarView) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.value(48.dp)
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            constraintSet = constrains,
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Image(painter = painterResource(id = R.drawable.settings_btn), contentDescription = "settings button",
                modifier = Modifier
                    .layoutId("settings")
                    .clickable(true) {
                        onSettingsClicked()
                    })
            Box(modifier = Modifier
                .layoutId("clock_text")
                .wrapContentSize(align = Alignment.Center)) {
                Text(
                    text = "Clock",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Fonts.lexendDeca,
                    fontWeight = FontWeight.W400,
                )
            }
            Box(modifier = Modifier
                .layoutId("time")
                .wrapContentSize(align = Alignment.Center)) {
                Text(
                    text = timeText,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Fonts.lexendDeca,
                    fontWeight = FontWeight.W400,
                )
            }
            Box(modifier = Modifier
                .layoutId("date")
                .wrapContentSize(align = Alignment.Center)) {
                Text(
                    text = dateText,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Fonts.lexendDeca,
                    fontWeight = FontWeight.W400,
                )
            }
            Box(modifier = Modifier
                .layoutId("line")
                .background(color = MaterialTheme.colorScheme.secondary)
            )
            FloatingActionButton(modifier = Modifier
                .layoutId("fab")
                .shadow(elevation = 8.dp, shape = CircleShape),
                containerColor = MaterialTheme.colorScheme.secondary,
                onClick = { onFABClicked() },
            ) {
                Image(painter = painterResource(
                    id = if (isSystemInDarkTheme()) R.drawable.chronos_btn_purple else R.drawable.chronos_btn_red),
                    contentDescription = "fab icon", modifier = Modifier.size(26.dp, 26.dp))
            }
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .layoutId("convert_time_bar")
                    .background(MaterialTheme.colorScheme.inverseSurface)
                    .clickable(true) {
                        onConvertClicked()
                    }) {
                Text(
                    text = "Convert Time Zone",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Fonts.exo,
                    fontWeight = FontWeight.W400
                )
            }
        }
    }



}
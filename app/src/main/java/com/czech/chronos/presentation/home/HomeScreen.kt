package com.czech.chronos.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import com.czech.chronos.R
import com.czech.chronos.utils.Fonts
import com.czech.chronos.presentation.navigation.Screens
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun HomeScreen(navController: NavController) {

    val timeFormat = SimpleDateFormat("HH:mm")
    val dateFormat = SimpleDateFormat("EEE, d MMM")

    var getTime by remember{ mutableStateOf(timeFormat.format(Calendar.getInstance().time)) }

    DisposableEffect(true) {
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                getTime = timeFormat.format(Calendar.getInstance().time)
            }

        }
        timer.schedule(task, 0, 1000)

        onDispose {
            timer.cancel()
        }
    }

    var getDate by remember{ mutableStateOf(dateFormat.format(Calendar.getInstance().time)) }
    DisposableEffect(true) {
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                getDate = dateFormat.format(Calendar.getInstance().time)
            }

        }
        timer.schedule(task, 0, 1000)

        onDispose {
            timer.cancel()
        }
    }

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

    ConstraintLayout(constraintSet = constrains, modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {
        Image(painter = painterResource(id = R.drawable.settings), contentDescription = "settings button",
            modifier = Modifier
                .layoutId("settings")
                .clickable(enabled = true) {
                    navController.navigate(Screens.SettingsScreen.route)
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
                text = getTime,
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
                text = getDate,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = Fonts.lexendDeca,
                fontWeight = FontWeight.W400,
            )
        }
        Box(modifier = Modifier
            .layoutId("line")
            .background(color = MaterialTheme.colorScheme.secondary))
        FloatingActionButton(modifier = Modifier
            .layoutId("fab")
            .shadow(elevation = 8.dp, shape = CircleShape)
            .background(MaterialTheme.colorScheme.secondary),
            onClick = {
                navController.navigate(Screens.SearchScreen.route)
            }) {
            Image(painter = painterResource(id = R.drawable.chronos_btn),
                contentDescription = "fab icon", modifier = Modifier.size(26.dp, 26.dp))
        }
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .layoutId("convert_time_bar")
                .background(MaterialTheme.colorScheme.surface)
                .clickable(enabled = true, onClick = {
                    navController.navigate(Screens.ConvertScreen.route)
                })) {
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
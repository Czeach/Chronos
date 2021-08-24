package com.example.chronos.uis.home

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.chronos.R
import com.example.chronos.utils.Fonts
import com.example.chronos.utils.Screens
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current

    val timeFormatter = SimpleDateFormat("HH:mm")

    var getTime by remember{ mutableStateOf(timeFormatter.format(Calendar.getInstance().time)) }
    DisposableEffect(true) {
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                getTime = timeFormatter.format(Calendar.getInstance().time)
            }

        }
        timer.schedule(task, 0, 1000)

        onDispose {
            timer.cancel()
        }
    }

    val constrains = ConstraintSet {
        val settingsBtn = createRefFor("settings")
        val clockText = createRefFor("clock_text")
        val time = createRefFor("time")
        val date = createRefFor("date")
        val line = createRefFor("line")
        val fabBtn = createRefFor("fab")
        val convertTimeBar = createRefFor("convert_time_bar")

        constrain(settingsBtn) {
            top.linkTo(parent.top, margin = 20.dp)
            end.linkTo(parent.end, margin = 20.dp)
            width = Dimension.value(20.dp)
            height = Dimension.value(20.dp)
        }
        constrain(clockText) {
            top.linkTo(settingsBtn.bottom, margin = 6.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(time) {
            top.linkTo(clockText.bottom, margin = 18.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(date) {
            top.linkTo(time.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(line) {
            top.linkTo(date.bottom, margin = 60.dp)
            start.linkTo(parent.start, margin = 30.dp)
            end.linkTo(parent.end, margin = 30.dp)
            width = Dimension.fillToConstraints
            height = Dimension.value(0.5.dp)
        }
        constrain(fabBtn) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(convertTimeBar.top, margin = 48.dp)
            height = Dimension.value(56.dp)
            width = Dimension.value(56.dp)
        }
        constrain(convertTimeBar) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.value(48.dp)
        }
    }

    ConstraintLayout(constraintSet = constrains, modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)) {
        Image(painter = painterResource(id = R.drawable.settings_btn), contentDescription = "settings button",
            modifier = Modifier
                .layoutId("settings")
                .clickable(enabled = true,
                    onClick = {
                        navController.navigate(Screens.SettingsScreen.route)
                    }
                )
        )
        Box(modifier = Modifier
            .layoutId("clock_text")
            .wrapContentSize(align = Alignment.Center)) {
            Text(
                text = "Clock",
                color = MaterialTheme.colors.primary,
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
                color = MaterialTheme.colors.secondaryVariant,
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
                text = "Tue, 18 may",
                color = MaterialTheme.colors.primary,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = Fonts.lexendDeca,
                fontWeight = FontWeight.W400,
            )
        }
        Box(modifier = Modifier
            .layoutId("line")
            .background(color = MaterialTheme.colors.secondaryVariant))
        FloatingActionButton(modifier = Modifier
            .layoutId("fab")
            .shadow(elevation = 8.dp, shape = CircleShape)
            .background(MaterialTheme.colors.secondaryVariant),
            onClick = {
                navController.navigate(Screens.SearchScreen.route)
            }) {
            Icon(painter = painterResource(id = R.drawable.chronos_btn),
                contentDescription = "fab icon", modifier = Modifier.size(26.dp, 26.dp))
        }
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .layoutId("convert_time_bar")
                .background(MaterialTheme.colors.surface)
                .clickable(enabled = true, onClick = {
                    navController.navigate(Screens.ConvertScreen.route)
                })) {
            Text(
                text = "Convert Time Zone",
                color = MaterialTheme.colors.primary,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontFamily = Fonts.exo,
                fontWeight = FontWeight.W400
            )
        }
    }
}
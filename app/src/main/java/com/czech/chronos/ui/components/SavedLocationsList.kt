package com.czech.chronos.ui.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.utils.DateUtil
import com.czech.chronos.utils.Fonts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.time.*
import kotlin.math.absoluteValue
import kotlin.time.toKotlinDuration

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SavedLocationsList(
	list: List<CurrentTime>,
	modifier: Modifier = Modifier
) {
	LazyColumn(
		modifier = modifier
			.layoutId("locations")
	) {
		items(
			items = list
		) { data ->
			SavedLocationsItem(
				data = data,
				modifier = modifier
			)
		}
	}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SavedLocationsItem(
	data: CurrentTime,
	modifier: Modifier,
) {

	val timeFormatter = remember { DateUtil.timeFormat }

	var locationTime by remember {
		mutableStateOf(
			ZonedDateTime.now(ZoneId.of(data.timezoneLocation))
		)
	}

	LaunchedEffect(Unit) {
		withContext(Dispatchers.IO) {
			while (true) {
				locationTime = ZonedDateTime.now(ZoneId.of(data.timezoneLocation))
				delay(1000L)
			}
		}
	}

	val userTime = LocalDateTime.now()
	val hourDiff = Duration.between(userTime, ZonedDateTime.now(ZoneId.of(data.timezoneLocation))).toHours().toInt()

	var difference by remember {
		mutableStateOf("$hourDiff HOURS AHEAD")
	}

	when {
		hourDiff == 0 -> {
			difference = "NO DIFFERENCE"
		}
		hourDiff == -1 -> {
			difference = "${hourDiff.absoluteValue} HOUR BEHIND"
		}
		hourDiff < -1 -> {
			difference = "${hourDiff.absoluteValue} HOURS BEHIND"
		}
		hourDiff == 1 -> {
			difference = "$hourDiff HOUR AHEAD"
		}
	}

	Box(
		modifier = modifier
			.padding(bottom = 6.dp)
			.fillMaxWidth()
			.wrapContentHeight()
			.padding(start = 18.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
	) {
		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = modifier
				.fillMaxWidth()
				.fillMaxHeight()
		) {
			Column(
				horizontalAlignment = Alignment.Start,
				modifier = modifier
			) {
				Text(
					text = data.requestedLocation.toString(),
					color = MaterialTheme.colorScheme.inversePrimary,
					fontSize = 20.sp,
					fontFamily = Fonts.exo,
					fontWeight = FontWeight.W400,
					modifier = modifier
				)

				Text(
					text = difference,
					color = MaterialTheme.colorScheme.tertiary,
					fontSize = 12.sp,
					fontFamily = Fonts.exo,
					fontWeight = FontWeight.W400,
					modifier = modifier
						.padding(top = 1.dp)
				)
			}
			Text(
				text = timeFormatter.format(locationTime),
				color = MaterialTheme.colorScheme.primary,
				fontSize = 28.sp,
				fontFamily = Fonts.lexendDeca,
				fontWeight = FontWeight.W400,
				modifier = modifier
			)
		}
	}
}
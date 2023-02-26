package com.czech.chronos.ui.screens

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.CalendarContract
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.czech.chronos.ui.components.AppBar
import com.czech.chronos.ui.components.ConvertResult
import com.czech.chronos.utils.DateUtil
import com.czech.chronos.utils.Fonts
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.time.temporal.TemporalAccessor
import java.util.*
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertScreen(
	onBackPressed: () -> Unit,
	viewModel: HomeViewModel
) {
    Scaffold(
		topBar = {
			AppBar(
				title = {
					Text(
						text = "Convert Timezone",
						color = MaterialTheme.colorScheme.primary,
						fontSize = 16.sp,
						fontFamily = Fonts.lexendDeca,
						fontWeight = FontWeight.W400
					)
				},
				onBackPressed = { onBackPressed() }
			)
		}
	) { padding ->
		Box(
			modifier = Modifier
				.padding(padding)
				.fillMaxSize()
		) {

			val context = LocalContext.current

			if (viewModel.convertTimeResult.value != null) {
				val result = viewModel.convertTimeResult.value?.targetLocation
				val originalFormat = LocalDateTime.parse(result?.datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
				val time = remember {
					mutableStateOf(originalFormat.format(DateUtil.timeFormat))
				}
				val date = remember {
					mutableStateOf(originalFormat.format(DateUtil.longDateFormat))
				}
				val city = remember {
					mutableStateOf(result?.requestedLocation.toString())
				}

				val originalOffset = result?.gmtOffset?.toInt()
				val gmtOffset = remember {
					mutableStateOf("")
				}
				if (originalOffset != null) {
					when {
						originalOffset in -9..0 -> {
							gmtOffset.value = "GMT -0${originalOffset.absoluteValue}:00"
						}
						originalOffset < -9 -> {
							gmtOffset.value = "GMT -${originalOffset.absoluteValue}:00"
						}
						originalOffset in 1..9 -> {
							gmtOffset.value = "GMT +0${originalOffset.absoluteValue}:00"
						}
						originalOffset > 9 -> {
							gmtOffset.value = "GMT +${originalOffset.absoluteValue}:00"
						}
					}
				}
				ConvertResult(
					date = date,
					time = time,
					city = city,
					gmtOffset = gmtOffset,
					onAddToCalendarClicked = {
						addToCalendar(
							context = context,
							originalFormat = originalFormat
						)
					}
				)
			}
		}
	}
}


@RequiresApi(Build.VERSION_CODES.O)
private fun addToCalendar(context: Context, originalFormat:  LocalDateTime) {

	val intent = Intent(Intent.ACTION_INSERT)

	val cal = Calendar.getInstance()
	cal.set(Calendar.HOUR_OF_DAY, originalFormat.format(DateTimeFormatter.ofPattern("HH")).toInt())
	cal.set(Calendar.MINUTE, originalFormat.format(DateTimeFormatter.ofPattern("mm")).toInt())
	cal.set(Calendar.MONTH, getMonthNumber(originalFormat.format(DateTimeFormatter.ofPattern("MMM"))))
	cal.set(Calendar.DAY_OF_MONTH, originalFormat.format(DateTimeFormatter.ofPattern("d")).toInt())
	cal.set(Calendar.YEAR, originalFormat.format(DateTimeFormatter.ofPattern("YYYY")).toInt())

	intent.data = CalendarContract.Events.CONTENT_URI
	intent.putExtra(CalendarContract.Events.TITLE, "Enter a title")
	intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis)
	intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)

	startActivity(context, Intent.createChooser(intent, ""), null)
}
@RequiresApi(Build.VERSION_CODES.O)
private fun getMonthNumber(monthName: String?): Int {
	val dtFormatter = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH)
	val temporalAccessor: TemporalAccessor = dtFormatter.parse(monthName)
	return temporalAccessor.get(ChronoField.MONTH_OF_YEAR)
}

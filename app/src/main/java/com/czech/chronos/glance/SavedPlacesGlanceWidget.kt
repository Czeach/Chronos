package com.czech.chronos.glance

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.repositories.convert.ConvertTimeRepository
import com.czech.chronos.repositories.places.PlacesRepository
import com.czech.chronos.room.useCases.CurrentTimeDaoUseCase
import com.czech.chronos.ui.screens.HomeViewModel
import com.czech.chronos.utils.DateUtil
import com.czech.chronos.utils.Fonts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.O)
class SavedPlacesGlanceWidget @Inject constructor (
): GlanceAppWidget() {

	@Inject lateinit var currentTimeDaoUseCase: CurrentTimeDaoUseCase
	@Inject lateinit var placesRepository: PlacesRepository
	@Inject lateinit var convertTimeRepository: ConvertTimeRepository

	private val viewModel = HomeViewModel(
		convertTimeRepository = convertTimeRepository,
		placesRepository = placesRepository,
		currentTimeDaoUseCase = currentTimeDaoUseCase
	)

	@Composable
	override fun Content() {
		WidgetList(list = viewModel.savedLocations.collectAsState().value)
	}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WidgetList(
	list: List<CurrentTime>,
	modifier: GlanceModifier = GlanceModifier
) {
	LazyColumn(
		modifier = GlanceModifier
	) {
		items(
			items = list
		) { data ->
//			SavedLocationsItem(
//				data = data,
//				modifier = modifier as Modifier
//			)
		}
	}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WidgetItem(
	data: CurrentTime,
	modifier: GlanceModifier,
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

//	Box(
//		modifier = GlanceModifier
//			.padding(bottom = 6.dp)
//			.fillMaxWidth()
//			.wrapContentHeight()
//			.padding(start = 18.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
//	) {
//		Row(
////			horizontalArrangement = Arrangement.SpaceBetween,
//			modifier = GlanceModifier
//				.fillMaxWidth()
//				.fillMaxHeight()
//		) {
//			Column(
//				horizontalAlignment = Alignment.Start,
//				modifier = GlanceModifier
//			) {
//				Text(
//					text = data.requestedLocation.toString(),
//					color = MaterialTheme.colorScheme.inversePrimary,
//					fontSize = 20.sp,
//					fontFamily = Fonts.exo,
//					fontWeight = FontWeight.W400,
//					modifier = Modifier
//				)
//				Text(
//					text = difference,
//					style = TextStyle(),
//					color = MaterialTheme.colorScheme.tertiary,
//					fontSize = 12.sp,
//					fontFamily = Fonts.exo,
//					fontWeight = FontWeight.W500,
//					modifier = Modifier
//						.padding(top = 1.dp)
//				)
//			}
//			Text(
//				text = timeFormatter.format(locationTime),
//				color = MaterialTheme.colorScheme.primary,
//				fontSize = 28.sp,
//				fontFamily = Fonts.lexendDeca,
//				fontWeight = FontWeight.W400,
//				modifier = Modifier
//			)
//		}
//	}
}
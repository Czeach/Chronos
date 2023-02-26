package com.czech.chronos.ui.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.czech.chronos.R
import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.utils.Fonts
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertBottomSheetContent(
	time: MutableState<TextFieldValue>,
	date: MutableState<TextFieldValue>,
	homeInput: MutableState<TextFieldValue>,
	targetInput: MutableState<TextFieldValue>,
	homePredictions:  List<PlacePredictions.Prediction?>?,
	targetPredictions:  List<PlacePredictions.Prediction?>?,
	onHomePredictionClick: (String) -> Unit,
	onTargetPredictionClick: (String) -> Unit,
	onConvertClicked: () -> Unit,
	context: Context,
	modifier: Modifier = Modifier
) {

	val constrains = ConstraintSet {
		val header = createRefFor("header")
		val subHeader = createRefFor("sub_header")
		val homeLocText = createRefFor("home_location_text")
		val homeLocInput = createRefFor("home_location_input")
		val dateText = createRefFor("date_text")
		val dateInput = createRefFor("date_input")
		val targetLocText = createRefFor("target_location_text")
		val targetLocInput = createRefFor("target_location_input")
		val convertButton = createRefFor("convert_button")
		val homePrediction = createRefFor("home_prediction")
		val targetPrediction = createRefFor("target_prediction")

		constrain(header) {
			top.linkTo(parent.top, margin = 46.dp)
			start.linkTo(parent.start)
			end.linkTo(parent.end)
		}
		constrain(subHeader) {
			top.linkTo(header.bottom, margin = 14.dp)
			start.linkTo(header.start)
			end.linkTo(header.end)
		}
		constrain(homeLocText) {
			top.linkTo(subHeader.bottom, margin = 36.dp)
			start.linkTo(parent.start, margin = 34.dp)
		}
		constrain(homePrediction) {
			start.linkTo(homeLocInput.start)
			end.linkTo(homeLocInput.end)
			top.linkTo(parent.top, margin = 4.dp)
			bottom.linkTo(homeLocInput.top)
			height = Dimension.fillToConstraints
			width = Dimension.fillToConstraints
		}
		constrain(homeLocInput) {
			top.linkTo(homeLocText.bottom, margin = 8.dp)
			start.linkTo(parent.start, margin = 34.dp)
			end.linkTo(parent.end, margin = 34.dp)
			width = Dimension.fillToConstraints
		}
		constrain(dateText) {
			top.linkTo(homeLocInput.bottom, margin = 20.dp)
			start.linkTo(homeLocText.start)
		}
		constrain(dateInput) {
			top.linkTo(dateText.bottom, margin = 8.dp)
			start.linkTo(homeLocInput.start)
			end.linkTo(homeLocInput.end)
			width = Dimension.fillToConstraints
		}
		constrain(targetLocText) {
			top.linkTo(dateInput.bottom, margin = 20.dp)
			start.linkTo(dateText.start)
		}

		constrain(targetPrediction) {
			start.linkTo(targetLocInput.start)
			end.linkTo(targetLocInput.end)
			bottom.linkTo(targetLocInput.top)
			width = Dimension.fillToConstraints
		}
		constrain(targetLocInput) {
			top.linkTo(targetLocText.bottom, margin = 8.dp)
			start.linkTo(dateInput.start)
			end.linkTo(dateInput.end)
			width = Dimension.fillToConstraints
		}
		constrain(convertButton) {
			top.linkTo(targetLocInput.bottom, margin = 34.dp)
			start.linkTo(targetLocInput.start, margin = 24.dp)
			end.linkTo(targetLocInput.end, margin = 24.dp)
			width = Dimension.fillToConstraints
			height = Dimension.value(60.dp)
		}
	}

	val timePicker = TimePickerDialog(
		context,
		if (isSystemInDarkTheme()) R.style.PickerThemeDark else R.style.PickerThemeLight,
		{_: TimePicker, hour: Int, minute:Int ->

			val hourString = if (hour < 10) "0$hour" else hour.toString()
			val minuteString = if (minute < 10) "0$minute" else minute.toString()

			time.value = TextFieldValue("$hourString:$minuteString")

		},
		0,
		0,
		true,
	)

	val cal: Calendar = Calendar.getInstance()
	val mYear: Int = cal.get(Calendar.YEAR)
	val mMonth: Int = cal.get(Calendar.MONTH)
	val mDay: Int = cal.get(Calendar.DAY_OF_MONTH)
	val datePicker = DatePickerDialog(
		context,
		if (isSystemInDarkTheme()) R.style.PickerThemeDark else R.style.PickerThemeLight,
		{_: DatePicker, year: Int, month: Int, day: Int ->
			val realMonth = month + 1
			val dayString = if (day < 10) "0$day" else day.toString()
			val monthString = if (realMonth < 10) "0$realMonth" else realMonth.toString()
			val yearString = year.toString()

			date.value = TextFieldValue("$yearString-$monthString-$dayString")
		},
		mYear,
		mMonth,
		mDay
	)

	ConstraintLayout(
		constraintSet = constrains,
		modifier = modifier
			.wrapContentHeight()
			.fillMaxWidth()
			.background(MaterialTheme.colorScheme.inverseSurface)
			.padding(bottom = 80.dp)
	) {
		Text(
			text = "Convert Timezone",
			color = MaterialTheme.colorScheme.primary,
			fontSize = 18.sp,
			fontFamily = Fonts.lexendDeca,
			fontWeight = FontWeight.W400,
			modifier = Modifier
				.layoutId("header")
		)
		Text(
			text = "Enter time, date and location to begin.",
			color = MaterialTheme.colorScheme.primary,
			fontSize = 14.sp,
			fontFamily = Fonts.exo,
			fontWeight = FontWeight.W400,
			modifier = Modifier
				.layoutId("sub_header")
		)
		Text(
			text = "Enter Home Location",
			color = MaterialTheme.colorScheme.primary,
			fontSize = 14.sp,
			fontFamily = Fonts.exo,
			fontWeight = FontWeight.W400,
			modifier = Modifier
				.layoutId("home_location_text")
		)
		if (homePredictions?.isNotEmpty() == true) {
			PredictionsResultList(
				list = homePredictions,
				onItemClick = onHomePredictionClick,
				modifier = Modifier
					.layoutId("home_prediction")
					.background(MaterialTheme.colorScheme.inverseSurface)
			)
		}

		TextField(
			value = homeInput.value,
			onValueChange = { newValue ->
				homeInput.value = newValue
			},
			placeholder = {
				Text(
					text = "Enter Location",
					fontSize = 13.sp,
					color = MaterialTheme.colorScheme.primary,
				)
			},
			singleLine = true,
			maxLines = 1,
			textStyle = TextStyle(
				fontSize = 16.sp,
				fontFamily = Fonts.exo,
				fontWeight = FontWeight.W500,
				color = MaterialTheme.colorScheme.primary,
			),
			shape = RoundedCornerShape(6.dp),
			colors = TextFieldDefaults.textFieldColors(
				containerColor = MaterialTheme.colorScheme.inverseSurface.copy(blue = 0.90F),
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent,
			),
			modifier = Modifier
				.layoutId("home_location_input")
		)
		Text(
			text = "Enter time and date",
			color = MaterialTheme.colorScheme.primary,
			fontSize = 14.sp,
			fontFamily = Fonts.exo,
			fontWeight = FontWeight.W400,
			modifier = Modifier
				.layoutId("date_text")
		)
		Row(
			modifier = Modifier
				.layoutId("date_input")
		) {
			TextField(
				value = time.value,
				onValueChange = { newValue ->
					time.value = newValue
				},
				placeholder = {
					Text(
						text = "00:00",
						fontSize = 13.sp,
						color = MaterialTheme.colorScheme.primary,
					)
				},
				enabled = false,
				singleLine = true,
				maxLines = 1,
				textStyle = TextStyle(
					fontSize = 16.sp,
					fontFamily = Fonts.exo,
					fontWeight = FontWeight.W500,
					color = MaterialTheme.colorScheme.primary,
				),
				shape = RoundedCornerShape(6.dp),
				colors = TextFieldDefaults.textFieldColors(
					containerColor = MaterialTheme.colorScheme.inverseSurface.copy(blue = 0.90F),
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent,
					disabledIndicatorColor = Color.Transparent,
					cursorColor = Color.Transparent
				),
				modifier = Modifier
					.weight(1F)
					.clickable(
						onClick = {
							timePicker.show()
						}
					)
			)
			Spacer(
				modifier = Modifier.width(16.dp)
			)
			TextField(
				value = date.value,
				onValueChange = { newValue ->
					date.value = newValue
				},
				placeholder = {
					Text(
						text = "yyyy-mm-dd",
						fontSize = 13.sp,
						color = MaterialTheme.colorScheme.primary,
					)
				},
				enabled = false,
				singleLine = true,
				maxLines = 1,
				textStyle = TextStyle(
					fontSize = 16.sp,
					fontFamily = Fonts.exo,
					fontWeight = FontWeight.W500,
					color = MaterialTheme.colorScheme.primary,
				),
				shape = RoundedCornerShape(6.dp),
				colors = TextFieldDefaults.textFieldColors(
					containerColor = MaterialTheme.colorScheme.inverseSurface.copy(blue = 0.90F),
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent,
					disabledIndicatorColor = Color.Transparent,
				),
				modifier = Modifier
					.weight(1.2F)
					.clickable(
						onClick = {
							datePicker.show()
						}
					)
			)
		}

		Text(
			text = "Enter Target Location",
			color = MaterialTheme.colorScheme.primary,
			fontSize = 14.sp,
			fontFamily = Fonts.exo,
			fontWeight = FontWeight.W400,
			modifier = Modifier
				.layoutId("target_location_text")
		)
		if (targetPredictions?.isNotEmpty() == true) {
			PredictionsResultList(
				list = targetPredictions,
				onItemClick = onTargetPredictionClick,
				modifier = Modifier
					.layoutId("target_prediction")
					.background(MaterialTheme.colorScheme.inverseSurface)
			)
		}
		TextField(
			value = targetInput.value,
			onValueChange = { newValue ->
				targetInput.value = newValue
			},
			placeholder = {
				Text(
					text = "Enter location",
					fontSize = 13.sp,
					color = MaterialTheme.colorScheme.primary,
				)
			},
			singleLine = true,
			maxLines = 1,
			textStyle = TextStyle(
				fontSize = 16.sp,
				fontFamily = Fonts.exo,
				fontWeight = FontWeight.W500,
				color = MaterialTheme.colorScheme.primary,
			),
			shape = RoundedCornerShape(6.dp),
			colors = TextFieldDefaults.textFieldColors(
				containerColor = MaterialTheme.colorScheme.inverseSurface.copy(blue = 0.9F),
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent,
			),
			modifier = Modifier
				.layoutId("target_location_input")
		)
		Button(
			modifier = Modifier
				.layoutId("convert_button"),
			onClick = { onConvertClicked() },
			colors = ButtonDefaults.buttonColors(
				containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65F)
			),
			shape = RoundedCornerShape(10.dp)
		) {
			Text(
				text = "Convert Time",
				textAlign = TextAlign.Center,
				fontSize = 16.sp,
				fontFamily = Fonts.lexendDeca,
				fontWeight = FontWeight.W400,
				color = MaterialTheme.colorScheme.primary,
				modifier = Modifier
			)
		}

	}
}
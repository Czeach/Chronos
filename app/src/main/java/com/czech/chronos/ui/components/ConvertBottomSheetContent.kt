package com.czech.chronos.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.czech.chronos.utils.Fonts

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ConvertBottomSheetContent(
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

	ConstraintLayout(
		constraintSet = constrains,
		modifier = modifier
			.wrapContentHeight()
			.fillMaxWidth()
			.background(MaterialTheme.colorScheme.inverseSurface)
			.padding(bottom = 80.dp)
	) {
		Text(
			text = "Convert Time Zone",
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
		TextField(
			value = "London, UK",
			onValueChange = {},
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
			text = "Enter Time and Date",
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
				value = "17:37",
				onValueChange = {},
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
					.weight(1F)
			)
			Spacer(
				modifier = Modifier.width(16.dp)
			)
			TextField(
				value = "25/01/2023",
				onValueChange = {},
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
					.weight(1.2F)
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
		TextField(
			value = "Tokyo, Japan",
			onValueChange = {},
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
			onClick = {},
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
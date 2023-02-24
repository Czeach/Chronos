package com.czech.chronos.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.czech.chronos.utils.Fonts

@Composable
fun ConvertResult(
	modifier: Modifier = Modifier,
	date: String,
	time: String,
	gmtOffset: String
) {

	val constrains = ConstraintSet {
		val dateTimeColumn = createRefFor("date_time")
		val buttonsColumn = createRefFor("buttons_column")

		constrain(dateTimeColumn) {
			top.linkTo(parent.top, margin = 40.dp)
			start.linkTo(parent.start, margin = 36.dp)
		}
		constrain(buttonsColumn) {
			bottom.linkTo(parent.bottom, margin = 120.dp)
			start.linkTo(parent.start)
			end.linkTo(parent.end)
		}
	}

	ConstraintLayout(
		constraintSet = constrains,
		modifier = modifier
			.fillMaxSize()
	) {
		Column(
			modifier = Modifier
				.layoutId("date_time")
		) {
			Text(
				text = time,
				color = MaterialTheme.colorScheme.secondary,
				fontSize = 60.sp,
				textAlign = TextAlign.Center,
				fontFamily = Fonts.lexendDeca,
				fontWeight = FontWeight.W400,
				modifier = Modifier
			)
			Text(
				text = date,
				color = MaterialTheme.colorScheme.primary,
				fontSize = 20.sp,
				textAlign = TextAlign.Center,
				fontFamily = Fonts.lexendDeca,
				fontWeight = FontWeight.W400,
				modifier = Modifier
					.padding(start = 4.dp)
			)
			Text(
				text = gmtOffset,
				color = MaterialTheme.colorScheme.primary,
				fontSize = 20.sp,
				textAlign = TextAlign.Center,
				fontFamily = Fonts.lexendDeca,
				fontWeight = FontWeight.W400,
				modifier = Modifier
					.padding(start = 4.dp)
			)

		}
		Column(
			modifier = Modifier
				.layoutId("buttons_column")
		) {
			Button(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 56.dp, end = 56.dp),
				onClick = {},
				colors = ButtonDefaults.buttonColors(
					containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.8F)
				),
				shape = RoundedCornerShape(10.dp)
			) {
				Text(
					text = "Add to calendar",
					textAlign = TextAlign.Center,
					fontSize = 16.sp,
					fontFamily = Fonts.lexendDeca,
					fontWeight = FontWeight.W400,
					color = MaterialTheme.colorScheme.primary,
					modifier = Modifier
						.padding(top = 14.dp, bottom = 14.dp)
				)
			}
			Spacer(
				modifier = Modifier
					.height(16.dp)
			)
			Button(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 56.dp, end = 56.dp),
				onClick = {},
				colors = ButtonDefaults.buttonColors(
					containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8F)
				),
				shape = RoundedCornerShape(10.dp)
			) {
				Text(
					text = "Set a reminder",
					textAlign = TextAlign.Center,
					fontSize = 16.sp,
					fontFamily = Fonts.lexendDeca,
					fontWeight = FontWeight.W400,
					color = MaterialTheme.colorScheme.primary,
					modifier = Modifier
						.padding(top = 14.dp, bottom = 14.dp)
				)
			}
		}
	}

}
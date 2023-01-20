package com.czech.chronos.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.utils.Fonts

@Composable
fun SavedLocationsList(
	list: List<CurrentTime>
) {
	LazyColumn {
		items(
			items = list
		) { data ->
			SavedLocationsItem(
				data = data
			)
		}
	}
}

@Composable
fun SavedLocationsItem(
	data: CurrentTime,
	modifier: Modifier = Modifier,
) {

	Box(
		modifier = modifier
			.fillMaxWidth()
			.height(50.dp)
	) {
		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically,
			modifier = modifier
				.fillMaxWidth()
				.fillMaxHeight()
		) {
			Column(
				horizontalAlignment = Alignment.Start,
				modifier = modifier
					.padding(start = 10.dp)
			) {
				Text(
					text = "Hong Kong",
					color = MaterialTheme.colorScheme.inversePrimary,
					fontSize = 16.sp,
					fontFamily = Fonts.exo,
					fontWeight = FontWeight.W400,
					modifier = modifier
						.padding(end = 14.dp)
				)

				Text(
					text = "7 hours ahead",
					color = MaterialTheme.colorScheme.tertiary,
					fontSize = 10.sp,
					fontFamily = Fonts.exo,
					fontWeight = FontWeight.W400,
					modifier = modifier
						.padding(end = 14.dp)
				)
			}
			Text(
				text = "03:36 AM",
				color = MaterialTheme.colorScheme.tertiary,
				fontSize = 24.sp,
				fontFamily = Fonts.lexendDeca,
				fontWeight = FontWeight.W400,
				modifier = modifier
					.padding(end = 14.dp)
			)
		}
	}
}
package com.czech.chronos.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.utils.Fonts

@Composable
fun PredictionsResultList(
    list: List<PlacePredictions.Prediction?>?,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list!!
        ) { predictions ->
            PredictionsResultItem(
                city = predictions?.description.toString(),
                onItemClick = {
                    onItemClick(predictions?.description.toString())
                }
            )
        }
    }
}

@Composable
fun PredictionsResultItem(
    city: String,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp)
            .clickable {
                onItemClick()
            },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = city,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            fontFamily = Fonts.exo,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .padding(start = 22.dp)
        )
    }
}
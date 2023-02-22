package com.czech.chronos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.czech.chronos.ui.components.AppBar
import com.czech.chronos.ui.components.ConvertResult
import com.czech.chronos.utils.Fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertScreen(
	onBackPressed: () -> Unit,
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
			ConvertResult()
		}
	}
}
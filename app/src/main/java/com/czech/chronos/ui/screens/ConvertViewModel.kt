package com.czech.chronos.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConvertViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle
): ViewModel() {

	val dateTime = mutableStateOf("")
	val offset = mutableStateOf(0)

	init {
		savedStateHandle.get<String>("date_time")?.let {
			dateTime.value = it
		}

		savedStateHandle.get<Int>("gmt_offset")?.let {
			offset.value = it
		}
	}
}
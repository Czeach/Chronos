package com.example.chronos.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.chronos.R

sealed class Fonts {

    companion object {
        val lexendDeca = FontFamily(
            Font(R.font.lexend_deca_regular)
        )
    }
}
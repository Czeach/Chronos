package com.czech.chronos.glance

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

@RequiresApi(Build.VERSION_CODES.O)
class SavedPlacesGlanceWidgetReceiver: GlanceAppWidgetReceiver() {
	override val glanceAppWidget: GlanceAppWidget
		get() = SavedPlacesGlanceWidget()

}
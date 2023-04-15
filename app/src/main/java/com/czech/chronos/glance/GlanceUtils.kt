package com.czech.chronos.glance

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class AppWidgetBroadcastReceiver : BroadcastReceiver() {

	override fun onReceive(context: Context, intent: Intent) {
		Toast.makeText(
			context,
			"Widget pinned successfully. Go to home screen.",
			Toast.LENGTH_SHORT
		).show()
	}
}

@RequiresApi(Build.VERSION_CODES.O)
fun AppWidgetProviderInfo.pin(context: Context) {
	val successCallback = PendingIntent.getBroadcast(
		context,
		0,
		Intent(context, AppWidgetBroadcastReceiver::class.java),
		PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
	)

	AppWidgetManager.getInstance(context).requestPinAppWidget(provider, null, successCallback)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PinWidget(
	providerInfo: AppWidgetProviderInfo
) {
	val context = LocalContext.current
	providerInfo.pin(context)
}
package com.czech.chronos

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Places.initialize(this, BuildConfig.GOOGLE_MAPS_API_KEY)
    }
}
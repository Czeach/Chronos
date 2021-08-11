package com.example.chronos.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChronosApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
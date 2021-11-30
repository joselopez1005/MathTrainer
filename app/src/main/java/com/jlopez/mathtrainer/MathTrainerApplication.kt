package com.jlopez.mathtrainer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MathTrainerApplication: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}
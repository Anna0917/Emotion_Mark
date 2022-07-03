package com.asi.emotionmark

import android.app.Application
import com.asi.emotionmark.data.repository.MarkRepositoryImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MarkRepositoryImpl.initialize(this)
    }
}
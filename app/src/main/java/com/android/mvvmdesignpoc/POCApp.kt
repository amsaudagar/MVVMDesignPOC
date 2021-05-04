package com.android.mvvmdesignpoc

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class POCApp : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: POCApp

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}
package com.android.mvvmdesignpoc

import android.app.Application
import android.content.Context
import com.android.mvvmdesignpoc.core.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() =
        startKoin {
            androidLogger()

            // use the Android context given there
            androidContext(this@POCApp.applicationContext)

            // module list
            modules(listOf(applicationModule))
        }
}
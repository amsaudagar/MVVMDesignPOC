package com.android.mvvmdesignpoc.core.platform

import android.content.Context
import com.android.mvvmdesignpoc.core.extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}
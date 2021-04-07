package com.android.mvvmdesignpoc.core.di

import com.android.mvvmdesignpoc.core.navigation.Navigator
import com.android.mvvmdesignpoc.core.platform.NetworkHandler
import com.android.mvvmdesignpoc.features.dashboard.data.remote.CountryDetailsService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val PROD = "https://dl.dropboxusercontent.com"
var CURRENT_ENV = PROD

const val REST_CONNECTION_TIMEOUT_MS = 20000L

val applicationModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(CURRENT_ENV)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {

        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        okHttpClientBuilder
            .callTimeout(REST_CONNECTION_TIMEOUT_MS * 2, TimeUnit.MILLISECONDS)
            .connectTimeout(REST_CONNECTION_TIMEOUT_MS * 2, TimeUnit.MILLISECONDS)
            .writeTimeout(REST_CONNECTION_TIMEOUT_MS * 2, TimeUnit.MILLISECONDS)
            .readTimeout(REST_CONNECTION_TIMEOUT_MS * 2, TimeUnit.MILLISECONDS)
        okHttpClientBuilder.build() as OkHttpClient
    }

    factory { NetworkHandler(get()) }
    factory { CountryDetailsService(get()) }
    single { Navigator() }
}
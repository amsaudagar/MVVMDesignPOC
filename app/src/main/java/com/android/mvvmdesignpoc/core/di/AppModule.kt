package com.android.mvvmdesignpoc.core.di

import android.content.Context
import com.android.mvvmdesignpoc.core.platform.NetworkHandler
import com.android.mvvmdesignpoc.features.dashboard.data.CountryDetailsRepository
import com.android.mvvmdesignpoc.features.dashboard.data.remote.CountryDetailsService
import com.android.mvvmdesignpoc.features.dashboard.usecase.CountryDetailsUseCase
import com.android.mvvmdesignpoc.features.dashboard.viewmodel.CountryDetailsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val PROD = "https://dl.dropboxusercontent.com"
    private var CURRENT_ENV = PROD
    private const val REST_CONNECTION_TIMEOUT_MS = 20000L

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(CURRENT_ENV)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*@Provides
    @Singleton
    @Named("Retrofit1")
    fun getRetrofit1(okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(CURRENT_ENV)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/

    @Provides
    @Singleton
    fun getOkHttpClient() : OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        okHttpClientBuilder
            .callTimeout(REST_CONNECTION_TIMEOUT_MS * 2, TimeUnit.MILLISECONDS)
            .connectTimeout(REST_CONNECTION_TIMEOUT_MS * 2, TimeUnit.MILLISECONDS)
            .writeTimeout(REST_CONNECTION_TIMEOUT_MS * 2, TimeUnit.MILLISECONDS)
            .readTimeout(REST_CONNECTION_TIMEOUT_MS * 2, TimeUnit.MILLISECONDS)

        return okHttpClientBuilder.build() as OkHttpClient
    }

    @Provides
    @Singleton
    fun getNetworkHandler(@ApplicationContext context: Context) = NetworkHandler(context)

    @Provides
    @Singleton
    @Named("details1")
    fun getCountryDetailsService(retrofit: Retrofit) = CountryDetailsService(retrofit)

/*
    @Provides
    @Singleton
    @Named("details2")
    fun getCountryDetailsService2(@Named("Retrofit1") retrofit: Retrofit) = CountryDetailsService(retrofit, "")
*/

    @Provides
    fun getCountryDetailsRepository(networkHandler : NetworkHandler,
                                    @Named("details1") countryDetailsService : CountryDetailsService)
            = CountryDetailsRepository.CountryDetailsRepositoryImp(networkHandler, countryDetailsService)

    @Provides
    fun getCountryDetailsUseCase(repository : CountryDetailsRepository.CountryDetailsRepositoryImp)
            = CountryDetailsUseCase(repository)

    @Provides
    fun getCountryDetailsViewModel(countryDetailsUseCase : CountryDetailsUseCase)
            = CountryDetailsViewModel(countryDetailsUseCase)
}
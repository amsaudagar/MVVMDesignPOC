package com.android.mvvmdesignpoc.features.dashboard.data.remote

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Service class to call the API
 */
class CountryDetailsService @Inject
 constructor(retrofit: Retrofit) : CountryDetailsApi {

    @Inject constructor(retrofit: Retrofit, str : String) {

    }

    private val api by lazy { retrofit.create(CountryDetailsApi::class.java) }

    override fun getCountryDetails() = api.getCountryDetails()
}
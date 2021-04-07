package com.android.mvvmdesignpoc.features.dashboard.data.remote

import retrofit2.Retrofit

/**
 * Service class to call the API
 */
class CountryDetailsService
constructor(retrofit: Retrofit) : CountryDetailsApi {

    private val api by lazy { retrofit.create(CountryDetailsApi::class.java) }

    override fun getCountryDetails() = api.getCountryDetails()
}
package com.android.mvvmdesignpoc.features.dashboard.data.remote

import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.CountryDetailsResponse
import retrofit2.Call
import retrofit2.http.GET

internal interface CountryDetailsApi {
    companion object {
        private const val GET_COUNTRY_DETAILS = "/s/2iodh4vg0eortkl/facts.json"
    }

    @GET(GET_COUNTRY_DETAILS)
    fun getCountryDetails(): Call<CountryDetailsResponse>
}
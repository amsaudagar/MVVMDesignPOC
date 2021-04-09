package com.android.mvvmdesignpoc.features.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import com.android.mvvmdesignpoc.core.interactor.UseCase
import com.android.mvvmdesignpoc.core.platform.BaseViewModel
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.CountryDetailsResponse
import com.android.mvvmdesignpoc.features.dashboard.usecase.CountryDetailsUseCase

/**
 * View model class to provide the data to view through rest API
 */
class CountryDetailsViewModel
constructor(private val countryDetailsUseCase : CountryDetailsUseCase) : BaseViewModel() {

    var countryDetails: MutableLiveData<CountryDetailsResponse> = MutableLiveData()

    /**
     * Fetches the country details
     */
    fun getCountryDetails()
            = countryDetailsUseCase(UseCase.None()) {
        it.either(::handleFailure, ::handleCountryDetailsResponse)
    }

    private fun handleCountryDetailsResponse(countryDetailsResponse: CountryDetailsResponse?) {
        countryDetails.value = countryDetailsResponse
    }
}
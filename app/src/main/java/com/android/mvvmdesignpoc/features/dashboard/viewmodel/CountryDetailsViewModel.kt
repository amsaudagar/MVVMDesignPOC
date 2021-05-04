package com.android.mvvmdesignpoc.features.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import com.android.mvvmdesignpoc.core.interactor.UseCase
import com.android.mvvmdesignpoc.core.platform.BaseViewModel
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.CountryDetailsResponse
import com.android.mvvmdesignpoc.features.dashboard.usecase.CountryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model class to provide the data to view through rest API
 */
@HiltViewModel
class CountryDetailsViewModel @Inject
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
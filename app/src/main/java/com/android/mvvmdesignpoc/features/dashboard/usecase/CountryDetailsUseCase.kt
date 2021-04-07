package com.android.mvvmdesignpoc.features.dashboard.usecase

import com.android.mvvmdesignpoc.core.exception.Failure
import com.android.mvvmdesignpoc.core.functional.Either
import com.android.mvvmdesignpoc.core.interactor.UseCase
import com.android.mvvmdesignpoc.features.dashboard.data.CountryDetailsRepository
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.CountryDetails

/**
 * Use case responsible to fetch country details through repository
 */
class CountryDetailsUseCase
constructor(private val repository: CountryDetailsRepository) :
        UseCase<CountryDetails, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, CountryDetails> = repository.getCountryDetails()
}
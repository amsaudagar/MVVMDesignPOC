package com.android.mvvmdesignpoc.features.dashboard.data

import com.android.mvvmdesignpoc.core.exception.Failure
import com.android.mvvmdesignpoc.core.functional.Either
import com.android.mvvmdesignpoc.core.platform.BaseRepository
import com.android.mvvmdesignpoc.core.platform.NetworkHandler
import com.android.mvvmdesignpoc.features.dashboard.data.remote.CountryDetailsService
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.CountryDetails

/**
 * Represents the repository responsible to make the REST API call using service class
 */
interface CountryDetailsRepository {
    fun getCountryDetails(): Either<Failure, CountryDetails>

    class UserRepositoryImp
    constructor(private val networkHandler: NetworkHandler,
                private val service: CountryDetailsService) : CountryDetailsRepository, BaseRepository() {

        override fun getCountryDetails(): Either<Failure, CountryDetails> {
            return when (networkHandler.isConnected) {
                true -> {
                    request(service.getCountryDetails(), {
                        it
                    }, CountryDetails.empty())
                }
                false, null -> {
                    Either.Left(Failure.NetworkConnection())
                }
            }
        }
    }
}
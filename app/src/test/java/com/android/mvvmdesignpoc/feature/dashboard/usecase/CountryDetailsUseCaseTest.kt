package com.android.mvvmdesignpoc.feature.dashboard.usecase

import com.android.mvvmdesignpoc.core.exception.Failure
import com.android.mvvmdesignpoc.core.functional.Either
import com.android.mvvmdesignpoc.core.interactor.UseCase
import com.android.mvvmdesignpoc.feature.dashboard.viewmodel.TestCoroutineRule
import com.android.mvvmdesignpoc.features.dashboard.data.CountryDetailsRepository
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.CountryDetailsResponse
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.Row
import com.android.mvvmdesignpoc.features.dashboard.usecase.CountryDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

/**
 * Unit test class for CountryDetailsUseCase
 */
class CountryDetailsUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun init() {
        // Resources can be initialized here
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCountryDetailsTest_success() {

        testCoroutineRule.runBlockingTest {

            val expectedTitle = "About Country"
            val rows: ArrayList<Row> = ArrayList()
            val title = "Beavers"
            val description = "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony"
            val imageHref = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
            rows.add(Row(title, description, imageHref))

            val repository = Mockito.mock(CountryDetailsRepository::class.java)
            val countryDetailsResponse = CountryDetailsResponse(expectedTitle, rows)
            `when`(repository.getCountryDetails()).thenReturn(Either.Right(countryDetailsResponse))

            val countryDetailsUseCase = CountryDetailsUseCase(repository)

            var response: CountryDetailsResponse? = null
            var failureResponse: Failure? = null

            countryDetailsUseCase(UseCase.None()) {
                it.either(fun(failure: Failure) {
                    failureResponse = failure
                }, fun(countryDetails: CountryDetailsResponse?) {
                    response = countryDetails
                })

                Assert.assertEquals(expectedTitle, response?.title)
                Assert.assertTrue(response?.rows?.isNotEmpty() == true)
                Assert.assertEquals(1, response?.rows?.size)
                Assert.assertEquals(title, response?.rows?.get(0)?.title)
                Assert.assertEquals(description, response?.rows?.get(0)?.description)
                Assert.assertEquals(imageHref, response?.rows?.get(0)?.imageHref)
                Assert.assertNull(failureResponse)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCountryDetailsTest_failed() {

        testCoroutineRule.runBlockingTest {
            val expectedStatus = "404"
            val repository = Mockito.mock(CountryDetailsRepository::class.java)
            val featureFailure = Failure.FeatureFailure(expectedStatus)
            `when`(repository.getCountryDetails()).thenReturn(Either.Left(featureFailure))

            val countryDetailsUseCase = CountryDetailsUseCase(repository)
            var response: CountryDetailsResponse? = null
            var failureResponse: Failure? = null

            countryDetailsUseCase(UseCase.None()) {
                it.either(fun(failure: Failure) {
                    failureResponse = failure
                }, fun(countryDetails: CountryDetailsResponse?) {
                    response = countryDetails
                })
                Assert.assertNull(response)
                Assert.assertTrue(failureResponse is Failure.FeatureFailure)
                Assert.assertEquals(expectedStatus, (failureResponse as Failure.FeatureFailure).code)
            }
        }
    }
}
package com.android.mvvmdesignpoc.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
//@InstallIn(ActivityComponent::class)
@InstallIn(FragmentComponent::class)
//@InstallIn(ActivityRetainedComponent::class)
object HomeModule {

//    @Provides
//    fun getCountryDetailsViewModel(countryDetailsUseCase : CountryDetailsUseCase)
//            = CountryDetailsViewModel(countryDetailsUseCase)
}
package com.android.mvvmdesignpoc.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mvvmdesignpoc.core.exception.Failure

/**
 * Represents the base view model to provide common features across view models
 */
abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}
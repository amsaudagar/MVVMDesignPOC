package com.android.mvvmdesignpoc.core.exception

sealed class Failure {
    class NetworkConnection : Failure()
    class ServerError : Failure()
    open class FeatureFailure(open val code: String) : Failure()
}

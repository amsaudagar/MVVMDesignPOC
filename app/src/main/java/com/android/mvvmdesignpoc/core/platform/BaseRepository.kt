package com.android.mvvmdesignpoc.core.platform

import com.android.mvvmdesignpoc.core.exception.Failure
import com.android.mvvmdesignpoc.core.functional.Either
import retrofit2.Call
import retrofit2.Response

/**
 * Base repository class to handle the server request
 */
open class BaseRepository {

    fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response: Response<T> = call.execute()
            if (response.isSuccessful)
                Either.Right(transform((response.body() ?: default)))
            else Either.Left(Failure.FeatureFailure((response.body() as BaseResponse).message?:""))

        } catch (exception: Throwable) {
            exception.printStackTrace()
            Either.Left(Failure.ServerError())
        }
    }
}
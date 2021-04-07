package com.android.mvvmdesignpoc.core.platform

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * All common response parameter can be declared here
 */
open class BaseResponse(

    @SerializedName("error")
    @Expose
    var error: Boolean = false,

    @SerializedName("message")
    @Expose
    var message: String? = ""
) {
    companion object {
        fun empty() = BaseResponse(false, "")
    }
}
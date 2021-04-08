package com.android.mvvmdesignpoc.features.dashboard.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryDetailsResponse(

    @SerializedName("title")
    @Expose
    var title: String? = "",

    @SerializedName("rows")
    @Expose
    val rows: ArrayList<Row> = arrayListOf()

) {
    companion object {
        fun empty() = CountryDetailsResponse()
    }
}

data class Row(

    @SerializedName("title")
    @Expose
    var title: String? = "",

    @SerializedName("description")
    @Expose
    var description: String? = "",

    @SerializedName("imageHref")
    @Expose
    var imageHref: String? = "")
package com.android.mvvmdesignpoc.features.dashboard.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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

@Parcelize
data class Row(

    @SerializedName("title")
    @Expose
    var title: String? = "",

    @SerializedName("description")
    @Expose
    var description: String? = "",

    @SerializedName("imageHref")
    @Expose
    var imageHref: String? = "") : Parcelable
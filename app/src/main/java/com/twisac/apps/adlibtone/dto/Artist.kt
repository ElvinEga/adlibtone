package com.twisac.apps.adlibtone.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Artist {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("rating")
    @Expose
    var rating: Float? = null
    @SerializedName("date")
    @Expose
    var date: String? = null

}
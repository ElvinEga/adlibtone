package com.twisac.apps.adlibtone.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AdlibDto {

    @SerializedName("artist")
    @Expose
    var artist: List<Artist>? = null

}
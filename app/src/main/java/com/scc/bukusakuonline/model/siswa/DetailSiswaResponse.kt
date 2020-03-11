package com.scc.bukusakuonline.model.siswa

import com.google.gson.annotations.SerializedName

data class DetailSiswaResponse(
        @SerializedName("data")
        val data : DetailSiswaItems
)
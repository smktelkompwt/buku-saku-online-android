package com.scc.bukusakuonline.user.model.siswa

import com.google.gson.annotations.SerializedName
import com.scc.bukusakuonline.user.model.siswa.DetailSiswaItems

data class DetailSiswaResponse(
        @SerializedName("data")
        val data : DetailSiswaItems
)
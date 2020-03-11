package com.scc.bukusakuonline.model.riwayat

import com.google.gson.annotations.SerializedName

data class LaporanByIdResponse(
        @SerializedName("data")
        val data : DataItem?
)
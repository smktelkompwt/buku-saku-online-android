package com.scc.bukusakuonline.model.Laporan

import com.google.gson.annotations.SerializedName

data class LaporanByIdResponse(
        @SerializedName("data")
        val data : DataItem ?
)
package com.scc.bukusakuonline.model.Laporan

import com.google.gson.annotations.SerializedName
import com.scc.bukusakuonline.model.Riwayat.DataItem

data class LaporanByIdResponse(
        @SerializedName("data")
        val data : DataItem?
)
package com.scc.bukusakuonline.model.detailpoint

import com.google.gson.annotations.SerializedName

data class DetailPointItems(
        @SerializedName("kode")
        val code : String ?,
        @SerializedName("jenis_pelanggaran")
        val jenis_pelanggaran : String ?,
        @SerializedName("point")
        val point : String ?
)
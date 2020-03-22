package com.scc.bukusakuonline.model.siswa

import com.google.gson.annotations.SerializedName

data class SiswaKelasResponse (
    @SerializedName("code")
    val code : Int ?,
    @SerializedName("data")
    val data : List<SiswaKelasItem> ?
)
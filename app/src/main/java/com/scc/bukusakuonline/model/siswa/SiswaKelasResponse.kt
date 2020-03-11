package com.scc.bukusakuonline.model.siswa

import com.google.gson.annotations.SerializedName
import com.scc.bukusakuonline.model.DetailPointItems

data class SiswaKelasResponse (
    @SerializedName("code")
    val code : Int ?,
    @SerializedName("data")
    val data : List<SiswaKelasItem> ?
)
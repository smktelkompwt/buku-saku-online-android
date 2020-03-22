package com.scc.bukusakuonline.user.model.siswa

import com.google.gson.annotations.SerializedName
import com.scc.bukusakuonline.user.model.siswa.SiswaKelasItem

data class SiswaKelasResponse (
    @SerializedName("code")
    val code : Int ?,
    @SerializedName("data")
    val data : List<SiswaKelasItem> ?
)
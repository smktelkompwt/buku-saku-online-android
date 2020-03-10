package com.scc.bukusakuonline.model.siswa

import com.google.gson.annotations.SerializedName

data class SiswaKelasItem(
        @SerializedName("name")
        val name : String ?,
        @SerializedName("nis")
        val nis : Double ?,
        @SerializedName("point")
        val point : String ?
)
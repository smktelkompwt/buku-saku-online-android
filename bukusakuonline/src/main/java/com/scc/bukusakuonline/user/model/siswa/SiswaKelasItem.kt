package com.scc.bukusakuonline.user.model.siswa

import com.google.gson.annotations.SerializedName

data class SiswaKelasItem(
        @SerializedName("_id")
        val id :  String?,
        @SerializedName("name")
        val name : String ?,
        @SerializedName("nis")
        val nis : Double ?,
        @SerializedName("point")
        val point : String ?
)
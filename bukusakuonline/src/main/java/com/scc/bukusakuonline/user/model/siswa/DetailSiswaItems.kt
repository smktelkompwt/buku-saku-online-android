package com.scc.bukusakuonline.user.model.siswa

import com.google.gson.annotations.SerializedName
import com.scc.bukusakuonline.user.model.riwayat.DataItem

data class DetailSiswaItems(
        @SerializedName("name")
        val name: String?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("kelas")
        val kelas: String?,
        @SerializedName("point")
        val point: Int?,
        @SerializedName("countPelanggaran")
        val jumlah: String?,
        @SerializedName("pelanggaran")
        val pelanggaran: List<DataItem>?

)
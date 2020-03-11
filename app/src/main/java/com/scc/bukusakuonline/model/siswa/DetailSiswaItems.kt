package com.scc.bukusakuonline.model.siswa

import com.google.gson.annotations.SerializedName
import com.scc.bukusakuonline.model.riwayat.DataItem

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
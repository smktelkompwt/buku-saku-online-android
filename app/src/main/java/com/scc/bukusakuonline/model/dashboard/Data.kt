package com.scc.bukusakuonline.model.dashboard

import com.google.gson.annotations.SerializedName


class Data (
    @SerializedName("countPelanggaran")
    val countPelanggaran : Int?,
    @SerializedName("countSiswa")
    val countSiswa : Int ?,
    @SerializedName("pelanggaran")
    val pelanggaran: List<DashboardViolation>?
)
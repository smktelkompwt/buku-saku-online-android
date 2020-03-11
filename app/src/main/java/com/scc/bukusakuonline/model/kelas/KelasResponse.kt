package com.scc.bukusakuonline.model.kelas

import com.google.gson.annotations.SerializedName


data class KelasResponse(
        @SerializedName("data")
        val data : List<KelasItems> ?
)
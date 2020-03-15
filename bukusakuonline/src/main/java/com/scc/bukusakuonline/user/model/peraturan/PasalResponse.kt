package com.scc.bukusakuonline.user.model.peraturan

import com.google.gson.annotations.SerializedName

data class PasalResponse(
        @SerializedName("code")
        val code : Int ?,
        @SerializedName("data")
        val data : List<PasalItems> ?
)
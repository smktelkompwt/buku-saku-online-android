package com.scc.bukusakuonline.user.model.peraturan

import com.google.gson.annotations.SerializedName

data class DetailPasalResponse(
        @SerializedName("code")
        val code : String? ,
        @SerializedName("data")
        val data: PasalItems?
)
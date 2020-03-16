package com.scc.bukusakuonline.user.model.detailpoint

import com.google.gson.annotations.SerializedName

data class DetailPointResponse (
        @SerializedName("code")
        val code : Int ?,
        @SerializedName("data")
        val data : List<DetailPointItems> ?
)
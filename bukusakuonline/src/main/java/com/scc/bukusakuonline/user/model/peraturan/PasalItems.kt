package com.scc.bukusakuonline.user.model.peraturan

import com.google.gson.annotations.SerializedName

data class PasalItems(
        @SerializedName("_id")
        val id : String ?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("desc")
        val desc : List<DetailPasalItems>?
)
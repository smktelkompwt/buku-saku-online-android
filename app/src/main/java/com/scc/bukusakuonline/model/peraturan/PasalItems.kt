package com.scc.bukusakuonline.model.peraturan

import com.google.gson.annotations.SerializedName

data class PasalItems(
        @SerializedName("id")
        val id : String ?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("desc")
        val desc : List<DetailPasal>?
)
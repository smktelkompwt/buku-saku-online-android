package com.scc.bukusakuonline.user.model.peraturan

import com.google.gson.annotations.SerializedName

data class PeraturanItems(
        @SerializedName("id")
        val id : String ?,
        @SerializedName("bab")
        val bab : String ?,
        @SerializedName("pasal")
        val pasal: List<PasalItems> ?
)
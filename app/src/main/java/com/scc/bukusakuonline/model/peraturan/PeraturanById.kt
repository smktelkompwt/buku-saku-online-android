package com.scc.bukusakuonline.model.peraturan

import com.google.gson.annotations.SerializedName

data class PeraturanById(        @SerializedName("code")
                                 val code : Int ?,
                                 @SerializedName("data")
                                 val data : PeraturanItems ?)
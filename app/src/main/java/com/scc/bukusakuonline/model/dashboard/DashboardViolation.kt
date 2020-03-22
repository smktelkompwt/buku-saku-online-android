package com.scc.bukusakuonline.model.dashboard

import com.google.gson.annotations.SerializedName

class DashboardViolation (
        @SerializedName("title")
        val title: String?,
        @SerializedName("count")
        val count : Int?
)
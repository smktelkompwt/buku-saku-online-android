package com.scc.bukusakuonline.model.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
        @SerializedName("code")
        val code : Int?,
        @SerializedName("data")
        val data: List<UserItem>?
)
package com.scc.bukusakuonline.user.model.user

import com.google.gson.annotations.SerializedName
import com.scc.bukusakuonline.user.model.user.UserItem

data class UserResponse(
        @SerializedName("code")
        val code : Int?,
        @SerializedName("data")
        val data: UserItem
)
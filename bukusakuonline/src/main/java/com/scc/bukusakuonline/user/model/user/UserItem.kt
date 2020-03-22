package com.scc.bukusakuonline.user.model.user

import com.google.gson.annotations.SerializedName

data class UserItem(
        @SerializedName("name")
        val name: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("point")
        val phone: String?,
        @SerializedName("photo")
        val photo: String?
)
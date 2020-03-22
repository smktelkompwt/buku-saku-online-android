package com.scc.bukusakuonline.model.user

import com.google.gson.annotations.SerializedName

data class UserItem(
        @SerializedName("name")
        val name: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("phone")
        val phone: String?,
        @SerializedName("photo")
        val photo: String?
)
package com.tv.instamenu.data.modal

import com.google.gson.annotations.SerializedName

data class LoginModal(

    @field:SerializedName("data")
    val data: LoginData? = null,

    @field:SerializedName("status")
    val status: Int? = null
)

data class LoginData(

    @field:SerializedName("access_level")
    val accessLevel: String? = null,

    @field:SerializedName("photourl")
    val photourl: String? = null,

    @field:SerializedName("access_type")
    val accessType: String? = null,

    @field:SerializedName("sessionKey")
    val sessionKey: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("roll_id")
    val rollId: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("roll_name")
    val rollName: String? = null,

    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("msg")
    val msg: String? = null,
    @field:SerializedName("restaurant_name")
    val restaurantName: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)

package com.numero.mvp_example.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class User(
        @field:SerializedName("id") var id: Long?,
        @field:SerializedName("name") var name: String?,
        @field:SerializedName("username") var userName: String?,
        @field:SerializedName("email") var email: String?,
        @field:SerializedName("phone") var phone: String?) : Serializable

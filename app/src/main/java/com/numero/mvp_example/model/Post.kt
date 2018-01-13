package com.numero.mvp_example.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Post(
        @field:SerializedName("userId") val userId: Long?,
        @field:SerializedName("id") val id: Long?,
        @field:SerializedName("title") val title: String?,
        @field:SerializedName("body") val body: String?) : Serializable

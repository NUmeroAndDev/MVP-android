package com.numero.mvp_example.model

import com.squareup.moshi.Json
import java.io.Serializable

class Post(@Json(name = "userId") val userId: Long?,
           @Json(name = "id") val id: Long?,
           @Json(name = "title") val title: String?,
           @Json(name = "body") val body: String?) : Serializable

package com.numero.mvp_example.model

import com.squareup.moshi.Json
import java.io.Serializable

class User(
        @Json(name = "id") var id: Long?,
        @Json(name = "name") var name: String?,
        @Json(name = "username") var userName: String?,
        @Json(name = "email") var email: String?,
        @Json(name = "phone") var phone: String?) : Serializable

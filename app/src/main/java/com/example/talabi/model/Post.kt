package com.example.talabi.model

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class Post(
    @SerializedName("userId")
    val userId: Int,
    val name: String,
    val email:String,
    val phone: String,
    val password:String
)

data class PostResponse(
    @SerializedName("userId")
    val id: Int,
    val name: String,
    val email:String,
    val phone: String,
    val message:String
)

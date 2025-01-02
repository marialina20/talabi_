package com.example.talabi.model

import com.google.gson.annotations.SerializedName

data class Post(
    val userId: Int,
 //   val title: String,
  //  val body: String,
    val name: String,
    val email:String,
    val phone: String,
    val password:String
)

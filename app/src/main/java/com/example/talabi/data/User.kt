package com.example.talabi.data

data class User(

    val id: Int,
    var name: String,
    var email: String,
    var phone: String,
    val address: String?=null,
    val profilePicture: String? = null,
    val password: String,
    val googleAccountId: String? = null
)

data class UserProfileRequest(
    val firstName: String,
    val phoneNumber: String,
    val email: String,
    val profilePicture: String? = null
)
data class LoginRequest(
    var email: String,
    var password: String
)
data class LoginResponse(
    val message: String,
    val user: User?
)


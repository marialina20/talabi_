package com.example.talabi.data

data class UserProfileRequest(
    val firstName: String,
    val phoneNumber: String,
    val email: String,
    val profilePicture: String? = null  // This will hold the base64-encoded profile picture
)

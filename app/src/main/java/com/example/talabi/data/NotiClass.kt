package com.example.talabi.data

//data class Notification(
//    val nom: String,
//    val img: Int,
//    val timee: String,
//    var isOpened: Boolean = false
//)
data class NotificationDto(
    val orderId: Int,
    val status: String,
    val updatedAt: String
)
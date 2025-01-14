package com.example.talabi

//data class LoginResponse(
//    val message: String,
//    val userId: Int,
//    val userName: String,
//    val userEmail: String
//)
data class LoginResponse(
    val token: String,
    val userId: Int,
    val message: String
)


data class Loginclass(

    var email: String,
    val pass: String
)

data class Restaurant(
    val id: Int,
    val name: String,
    val logo: String,  // Nullable because logo can be null
    val address: String,
    val location: Location,
    val cuisine_type: String,
    val average_rating: Double,
    val number_of_reviews: Int,
    val contact_phone: String,
    val contact_email: String,
    val social_media_links: SocialMediaLinks
)

data class Location(
    val x: Double,
    val y: Double
)

data class SocialMediaLinks(
    val facebook: String? = null,
    val instagram: String? = null,
    val twitter: String? = null,
    val youtube: String? = null
)

data class Menu(
    val id: Int,
    val restaurant_id: Int,
    val name: String,
    val description: String,  // Nullable because logo can be null
    val image: String,
    val price: String,
    val availability_status: Int,
    val average_rating: Double

)
data class OrderResponse(
    val message: String,
    val orderId: Int
)


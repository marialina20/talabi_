package com.example.talabi.data

data class User(
    val id:Int,
    var name: String,
    var email: String,
    var phone: String,
    val address: String?=null,
    val profilePicture: String? = null,
    val password: String,
    val googleAccountId: String? = null
)
data class LoginRequest(
    var email: String,
    var password: String
)
data class LoginResponse(
    val message: String,
    val user: User? // Assuming `User` is a class with id, name, and email fields.
)

val users = listOf(
    User(1, "John Doe", "john@example.com", "1234567890", "123 Elm Street", null, "hashed_password_1", null),
    User(2, "Jane Smith", "jane@example.com", "0987654321", "456 Oak Avenue", "jane_profile.jpg", "hashed_password_2", "google_12345"),
    User(3, "Alice Brown", "alice@example.com", "5678901234", "789 Pine Lane", null, "hashed_password_3", null),
    User(4, "Bob White", "bob@example.com", "8765432109", "321 Cedar Court", "bob_avatar.png", "hashed_password_4", "google_54321"),
    User(5, "Charlie Black", "charlie@example.com", "1231231234", "987 Birch Boulevard", null, "hashed_password_5", null),
    User(6, "Diana Green", "diana@example.com", "4564564567", "654 Maple Drive", "diana_pic.jpeg", "hashed_password_6", "google_67890"),
    User(7, "Edward Blue", "edward@example.com", "7897897890", "321 Spruce Trail", null, "hashed_password_7", null),
    User(8, "Fiona Red", "fiona@example.com", "9879879876", "123 Fir Place", null, "hashed_password_8", "google_11223"),
    User(9, "George Yellow", "george@example.com", "5555555555", "456 Elm Row", "george_profile.png", "hashed_password_9", null),
    User(10, "Hannah Violet", "hannah@example.com", "1112223334", "789 Oak Terrace", null, "hashed_password_10", "google_44556")
)
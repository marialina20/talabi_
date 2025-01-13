package com.example.talabi.data

import com.example.talabi.R

data class Restaurant(
val id: Int,
val name: String,
val logo: String,
val image: Int,
val address: String,
val location: Pair<Double, Double>,
val cuisineType: String,
val averageRating: Float,
val numberOfReviews: Int,
val contactPhone: String,
val contactEmail: String,
val socialMediaLinks: Map<String, String>
)
val restaurants = listOf(
    Restaurant(1, "Italian Bistro", "italian_logo.png", R.drawable.rimg1,"123 Pasta Lane", 40.7128 to -74.0060, "Italian", 4.5f, 120, "1234567890", "info@italianbistro.com", mapOf("Instagram" to "insta.com/italianbistro")),
    Restaurant(2, "Sushi Palace", "sushi_logo.jpg",R.drawable.rimg2, "456 Sushi Avenue", 34.0522 to -118.2437, "Japanese", 4.7f, 98, "0987654321", "contact@sushipalace.com", mapOf("Facebook" to "fb.com/sushipalace")),
    Restaurant(3, "Taco Fiesta", "taco_logo.png", R.drawable.rimg3,"789 Taco Street", 29.7604 to -95.3698, "Mexican", 4.3f, 75, "5555555555", "support@tacofiesta.com", mapOf("Twitter" to "twitter.com/tacofiesta")),
    Restaurant(4, "Burger Hub", "burger_logo.jpg",R.drawable.rimg4, "101 Burger Blvd", 51.5074 to -0.1278, "American", 4.0f, 150, "4444444444", "hello@burgerhub.com", mapOf("Instagram" to "insta.com/burgerhub")),
    Restaurant(5, "Veggie Delight", "veggie_logo.png",R.drawable.rimg5, "202 Vegan Way", 48.8566 to 2.3522, "Vegan", 4.6f, 90, "3333333333", "contact@veggiedelight.com", mapOf("Facebook" to "fb.com/veggiedelight")),
    Restaurant(6, "Curry Corner", "curry_logo.jpg",R.drawable.rimg6, "303 Curry Road", 28.6139 to 77.2090, "Indian", 4.8f, 200, "9999999999", "info@currycorner.com", mapOf("Instagram" to "insta.com/currycorner")),
    Restaurant(7, "Pizza Planet", "pizza_logo.png",R.drawable.rimg7,"404 Pizza Place", 41.9028 to 12.4964, "Pizza", 4.2f, 110, "8888888888", "contact@pizzaplanet.com", mapOf("Twitter" to "twitter.com/pizzaplanet")),
    Restaurant(8, "Dim Sum Den", "dimsum_logo.jpg",R.drawable.rimg8, "505 Dumpling Drive", 22.3964 to 114.1095, "Chinese", 4.4f, 80, "7777777777", "support@dimsumden.com", mapOf("Instagram" to "insta.com/dimsumden")),
    Restaurant(9, "BBQ Barn", "bbq_logo.png",R.drawable.rimg9 ,"606 BBQ Lane", 35.6895 to 139.6917, "BBQ", 4.1f, 95, "6666666666", "info@bbqbarn.com", mapOf("Facebook" to "fb.com/bbqbarn")),
    Restaurant(10, "Seafood Shack", "seafood_logo.jpg",R.drawable.rimg10, "707 Ocean Drive", -33.8688 to 151.2093, "Seafood", 4.9f, 220, "5555556666", "contact@seafoodshack.com", mapOf("Instagram" to "insta.com/seafoodshack"))
)




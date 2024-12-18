package com.example.talabi.data

import com.example.talabi.R

data class MenuItem(
    val id: Int,
    val restaurantId: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: Int,
    val availabilityStatus: Boolean,
    val averageRating: Float,
)
val menuItems = listOf(
    MenuItem(1, 1, "Spaghetti Carbonara", "Classic Italian pasta with creamy sauce", 12.99, R.drawable.img7, true,4.5f),
    MenuItem(2, 1, "Margherita Pizza", "Wood-fired pizza with fresh mozzarella and basil", 9.99, R.drawable.img8, true,4.5f),
    MenuItem(3, 2, "Salmon Sashimi", "Fresh salmon slices served with soy sauce", 14.50, R.drawable.img10, true,4.5f),
    MenuItem(4, 2, "California Roll", "Crab, avocado, and cucumber wrapped in rice and seaweed", 8.99, R.drawable.img4, true,4.5f),
    MenuItem(5, 3, "lamp Tacos", "Soft corn tortillas filled with seasoned beef and toppings", 7.99, R.drawable.img5, true,4.5f),
    MenuItem(6, 3, "Chicken Quesadilla", "Grilled tortilla stuffed with cheese and chicken", 6.99, R.drawable.img6, false,4.5f),
    MenuItem(7, 4, "Classic Cheeseburger", "Grilled lamp patty with cheese, lettuce, and tomato,", 10.50, R.drawable.img9, true,4.5f),
    MenuItem(8, 4, "French Fries", "Crispy golden fries served with ketchup", 3.50, R.drawable.img10, true,4.5f),
    MenuItem(9, 5, "Vegan Buddha Bowl", "Quinoa, avocado, chickpeas, and veggies with tahini sauce", 11.99, R.drawable.img8, true,4.5f),
    MenuItem(10, 6, "Chicken Tikka Masala", "Tender chicken in a spiced tomato-cream sauce", 13.99, R.drawable.img4, true,4.5f)
)
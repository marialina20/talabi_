package com.example.talabi.Composants

import androidx.compose.runtime.Composable
import com.example.talabi.data.MenuItem
import com.example.talabi.data.Restaurant
import com.example.talabi.data.User
<<<<<<< Updated upstream
=======
import com.example.talabi.data.menuItems
>>>>>>> Stashed changes
import com.example.talabi.data.restaurants
import com.example.talabi.data.users

@Composable
fun getRestaurantById(id: Int): Restaurant? {
    return restaurants.find { it.id == id }
}

//@Composable
//fun getMenuItemById(id: Int): MenuItem? {
//    return menuItems.find { it.id == id }
//}
@Composable
fun getUserById(id: Int): User? {
    return users.find { it.id == id }
}
//@Composable
//fun getOrderById(id: Int): Order? {
//    return orders.find { it.id == id }
//}
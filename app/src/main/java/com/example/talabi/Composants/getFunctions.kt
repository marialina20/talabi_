package com.example.talabi.Composants

import androidx.compose.runtime.Composable
import com.example.talabi.data.MenuItem
import com.example.talabi.data.Orders
import com.example.talabi.data.Restaurant
import com.example.talabi.data.User
import com.example.talabi.data.menuItems
//import com.example.talabi.data.orders
import com.example.talabi.data.restaurants
import com.example.talabi.data.users

@Composable
fun getRestaurantById(id: Int): Restaurant? {
    return restaurants.find { it.id == id }
}

@Composable
fun getMenuItemById(id: Int): MenuItem? {
    return menuItems.find { it.id == id }
}
@Composable
fun getUserById(id: Int): User? {
    return users.find { it.id == id }
}
//@Composable
//fun getOrderById(id: Int): Orders? {
//    return orde
//}
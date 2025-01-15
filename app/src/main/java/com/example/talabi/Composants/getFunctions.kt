package com.example.talabi.Composants

import androidx.compose.runtime.Composable
import com.example.talabi.data.MenuItem
import com.example.talabi.data.Orders
import com.example.talabi.data.Restaurant
import com.example.talabi.data.User
//import com.example.talabi.data.orders
import com.example.talabi.data.restaurants


@Composable
fun getRestaurantById(id: Int): Restaurant? {
    return restaurants.find { it.id == id }
}

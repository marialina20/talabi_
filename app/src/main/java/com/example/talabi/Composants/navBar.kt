package com.example.talabi.ui.theme


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.navigation.compose.rememberNavController
import com.example.talabi.Destination
import com.example.talabi.NavigationScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyBottomNavigation() {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf("Home", "Notifications", "Cart", "Profile")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Notifications,
        Icons.Filled.ShoppingCart,
        Icons.Filled.Person
    )
    val navController= rememberNavController()
    Scaffold (bottomBar = {
    NavigationBar(
        containerColor = orange,
        tonalElevation = 3.dp,
        modifier = Modifier.clip(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.systemBars)
            .height(70.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.SpaceEvenly, // Evenly distribute items
            verticalAlignment = Alignment.CenterVertically // Center items vertically
        ) {

        items.forEachIndexed { index, item ->
            NavigationBarItem(

                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(
                        when (index) {
                            0 -> Destination.RestaurantMenu.route
                            1 -> Destination.Notification.route
                            2 -> Destination.Card.route
                            3 -> Destination.Profil.route
                            else -> Destination.RestaurantMenu.route
                        }
                    ) {
                        // Clear the back stack to avoid duplicate screens on the stack
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = item,
                        tint = if (selectedItem == index) blue else white
                    )
                },
                label = {
                    Text(
                        text = item,
                        color = if (selectedItem == index) blue else white
                    )
                }
            )
        }
        }
    }
    }){
            paddingValues->
        NavigationScreen(navController = navController, modifier = Modifier.padding(paddingValues))
    }
}

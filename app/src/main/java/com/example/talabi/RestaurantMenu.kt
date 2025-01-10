package com.example.talabi

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.privacysandbox.tools.core.model.Types.unit
import com.example.talabi.Composants.CircularAddButton
import com.example.talabi.Composants.DisplayRestaurantImage
import com.example.talabi.Composants.MenuItemImage
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.data.OrderItem
import com.example.talabi.ui.theme.AppTheme
import com.example.talabi.ui.theme.blue
import com.example.talabi.ui.theme.orange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DisplayRestaurantMenu(navController: NavHostController, restaurantId: String, sharedViewModel: SharedViewModel) {
    val userId = 9 // Exemple d'ID utilisateur
    val menuItemId = 8
    val quantity = 4
    val specialNotes = "nnnnnnnn "
    val message = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
   // val menu= remember { menuItems}
    Column (modifier = Modifier
        .padding(vertical = 24.dp, horizontal = 8.dp)) {
        DisplayRestaurantImage(restau_id = 1)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
        }
        var menuList by remember { mutableStateOf<List<Menu>>(emptyList()) }
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(restaurantId) {
            coroutineScope.launch {
                try {
                    // Fetch menu items by restaurant ID
                    val response = RetrofitInstance.api.getMenuItemsByRestaurantId(restaurantId)
                    if (response.isSuccessful) {
                        menuList = response.body() ?: emptyList()
                    } else {
                        Log.e("RestaurantMenuScreen", "Error: ${response.code()}")
                        Log.e("iddddddddddddddddddddd", "Error: ${restaurantId}")
                    }
                } catch (e: Exception) {
                    Log.e("RestaurantMenuScreen", "Error fetching menu: ${e.localizedMessage}")
                }
            }
        }

        LazyColumn ( contentPadding = PaddingValues(horizontal = 1.dp, vertical = 6.dp),
            modifier = Modifier
                .padding(WindowInsets.navigationBars.asPaddingValues())
                .padding(bottom = 30.dp)){

            items(menuList) { menuItem ->
                Card(
        modifier = Modifier
            .shadow(shape = RoundedCornerShape(16.dp), elevation = 10.02.dp)
            .padding(bottom = 5.dp)
            .fillMaxWidth()
            .clickable { navController.navigate(Destination.fooddescription.getDestination(menuItem.id)) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),

        ) {

        Row(
            modifier = Modifier.background(color = AppTheme.colors.background)
        ) {
            MenuItemImage(menuItem.image)
            Column(
                modifier = Modifier
                  //  .padding(1.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)

            ) {
               // Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.padding(1.dp),
                    text = " ${menuItem.name}",
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = blue
                    )

                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(3.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(3.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star icon",
                            tint = AppTheme.colors.secondarySurface, // Change the icon color
                            modifier = Modifier.size(24.dp) // Change the icon size
                        )
                        Text(
                            text = " ${menuItem.average_rating}",
                            style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 14.sp,color = blue)
                        )
                        //Text(text = " ${menuItem.price}",style= TextStyle(fontStyle = FontStyle.Italic, fontSize = 14.sp))
                    }
                    Spacer(modifier = Modifier.size(30.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(3.dp),
                    ) {
                        Text(
                            text = "$",
                            style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = orange
                            )
                        )
                        //Text(text = " ${menuItem.averageRating}",style= TextStyle(fontStyle = FontStyle.Italic, fontSize = 14.sp))
                        Text(
                            text = " ${menuItem.price}",
                            style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = orange
                            )
                        )
                    }
                }


//
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                ) {
                    CircularAddButton(
                        onClick = {

                            Log.e("iddddddddddddddddddddd", "Error: ${restaurantId}")
                            CoroutineScope(Dispatchers.IO).launch {
                                try {

                                    val newUser = OrderItem(
                                        userId = 2,           // Remplacez `orderId` par `userId`
                                        menuItemId = menuItem.id,
                                        quantity = 1,         // Assurez-vous qu'une valeur > 0 est utilisée
                                        specialNotes = ""
                                    )
                                    val response = RetrofitInstance.api.addToOrder(newUser)
                                    withContext(Dispatchers.Main) {
                                        if (response.isSuccessful) {
                                            sharedViewModel.setOrderId(response.body()!!.orderId)
                                            Log.e("truuuuuuuuuuuuuuuuuuuuuuuu", "Error: ${response.body()!!.orderId}")

                                        } else {
                                            Log.e("faaaaaaaaaaaaaaaaaaaalse", "Error: ${response.code()}")

                                        }
                                    }
                                } catch (e: Exception) {

                                }
                            }


                        },
                        content = "+",
                        contentColor = Color.White,
                        buttonColor = AppTheme.colors.actionSurface,
                        size = 50
                    )
                }

            }

        }
    }
            }
        }
    }}





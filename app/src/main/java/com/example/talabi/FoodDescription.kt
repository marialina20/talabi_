package com.example.talabi

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.talabi.Composants.CircularAddButton
import com.example.talabi.Composants.ExtendedButton
import com.example.talabi.Composants.FavoriteCirculedOutlineButton
import com.example.talabi.Composants.RatingDialog
import com.example.talabi.Composants.StarWithRatingDialog
import com.example.talabi.Composants.TopBar
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.api.RetrofitInstance.api
import com.example.talabi.data.MenuItem
import com.example.talabi.data.MenuItems
import com.example.talabi.data.OrderItem
import com.example.talabi.ui.theme.AppTheme
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.gray2
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun DisplayItemDiscreption(menuItemid:Int,navController: NavHostController,sharedViewModel: SharedViewModel) {
    println("-----------------------------${menuItemid}")
    var menuItem by remember { mutableStateOf(
        MenuItem(
            id = menuItemid,
            image = "",
            description = "hhhhh",
            restaurant_id = 0,
            name = "",
            availability_status = 1,
            average_rating = 4.5f,
            price = 0.0
        )
    ) }
    val userid = sharedViewModel.UserIdd
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val isLoading = remember { mutableStateOf(true) }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(menuItemid) {
        coroutineScope.launch {
            try {
                isLoading.value = true
                errorMessage.value = null
                val response = RetrofitInstance.api.getMenuItem(menuItemid)
                menuItem = response // Update menuItem state
                println("Updated menuItem:")
                println(menuItem) // This will print the updated state
            } catch (e: Exception) {
                errorMessage.value = "Erreur lors du chargement des données : ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    println("Current menuItem state:")
    println(menuItem)

    Box (
        modifier = Modifier.fillMaxSize() ,
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TopBar(
                content = "Description",
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onClick = { }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Box (
                    modifier = Modifier.background(orange)
                ){
                    Card(
                        modifier = Modifier
                            .background(Color.White)
                            .shadow(2.dp, shape = RoundedCornerShape(corner = CornerSize(16.dp)),),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardColors(
                            containerColor = white,
                            contentColor = Color.Transparent,
                            disabledContentColor = Color.Unspecified,
                            disabledContainerColor = Color.Unspecified
                        )

                    ) {
                        AsyncImage(model = menuItem!!.image, contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                //.padding(5.dp)
                                .size(height = 200.dp, width = 1000.dp)
                                .clip(RoundedCornerShape(corner = CornerSize(16.dp))))

                    }
                    Row (
                        modifier = Modifier.offset(x=265.dp,y=5.dp),
                        //horizontalArrangement = Arrangement.End

                    ){
                        FavoriteCirculedOutlineButton(imageVector = Icons.Filled.Favorite, sizeButton = 50, sizeIcon = 30, containerColor = Color(0xfFf1ebe9), borderStroke = 0.001f, borderColor = white,
                        )

                    }

                }
            }
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = menuItem.name,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            Column (
                modifier = Modifier
                    .padding(5.dp),
             //   verticalArrangement = Arrangement.spacedBy(.dp)
            ){
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "\$ ${menuItem.price}",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = orange)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    // modifier = Modifier.,
                )
                {

//                    RatingDialog()
                    StarWithRatingDialog(menuItem.id,userid)
                    Text(
                        text = " ${menuItem.average_rating}",
                        style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 16.sp)
                    )
                }
            }


            HorizontalDivider()
            Text(text ="Description:", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
           Text(text = menuItem.description, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal))

        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = white.copy(0.9f),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            shape = RoundedCornerShape(16.dp)

        )
        {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .fillMaxWidth()
            ) {

                ExtendedButton(content = "Add to my Card", imageVector = Icons.Filled.ShoppingCart,onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {

                            val newUser = OrderItem(
                                userId = sharedViewModel.UserIdd,           // Remplacez `orderId` par `userId`
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

                    Toast.makeText(context, "Item added to your Order", Toast.LENGTH_SHORT).show()
                })
            }

        }

    }
}
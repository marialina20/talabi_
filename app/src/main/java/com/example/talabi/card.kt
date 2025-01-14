import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talabi.Composants.CardItemImage
import com.example.talabi.Composants.CircularAddButton
import com.example.talabi.Composants.DialogScreen
import com.example.talabi.Composants.EditableTextDialog
import com.example.talabi.Composants.ExtendedButton
import com.example.talabi.Composants.TopBar
import com.example.talabi.Destination
import com.example.talabi.SharedViewModel
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.api.RetrofitInstance.api
import com.example.talabi.data.ApiResponse
import com.example.talabi.data.CartTotalResponse
import com.example.talabi.data.MenuItems
import com.example.talabi.data.RemoveItemRequest
import com.example.talabi.data.RemoveItemResponse
import com.example.talabi.data.UpdateNotesRequest
import com.example.talabi.data.UpdateOrderItemRequest
import com.example.talabi.data.UpdateQuantityRequest
import com.example.talabi.ui.theme.AppTheme
import com.example.talabi.ui.theme.blue
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.gray2
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white
import com.google.protobuf.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DisplayCardItems(navController: NavHostController,
                     sharedViewModel: SharedViewModel
) {
    var currentQuantity by remember { mutableStateOf(1) }

    var specialNotes by remember { mutableStateOf("") }
//    val updateRequest = UpdateOrderItemRequest(
//        orderItemId = 1, // Replace with the actual orderItemId
//        quantity = quantity, // Default to 1 if not entered
//        specialNotes = if (specialNotes.isNotBlank()) specialNotes else null
//    )
    //var numberofItem = 0
    val orderId = sharedViewModel.orderId
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val id = remember { mutableStateOf(0) }
    var prixtotal = remember { mutableStateOf(0.0)  }
    var menuList by remember { mutableStateOf<List<MenuItems>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(orderId) {
        coroutineScope.launch {
            try {
                // Fetch menu items by restaurant ID
                val response = RetrofitInstance.api.getOrderItems(1)
                if (response.isSuccessful) {
                    menuList = response.body() ?: emptyList()
                    Log.d("khaaaaaaayraaaaaaaa", "Error: ${menuList}")
                } else {
                    Log.e("hnnnnnnnnnnnnnnaaaaaaaaaaa", ": ${response.code()}")
                    Log.e("iddddddddddddddddddddd", "Error: ${orderId}")
                }
            } catch (e: Exception) {
                Log.e("RestaurantMenuScreen", "Error fetching menu: ${e.localizedMessage}")
            }
        }
    }
    Column {

       TopBar(content = "My Card", imageVector =  Icons.AutoMirrored.Filled.KeyboardArrowLeft,onClick = {})
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent) ,


        )
        {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .background(Color.Transparent)

            ) {
               // Spacer(modifier = Modifier.height(12.dp))
                //Text(text = "Check our famous Restaurants :",style= TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                    modifier = Modifier
                        .padding(WindowInsets.navigationBars.asPaddingValues())
                        .padding(bottom = 130.dp)

                    ) {
                    items(menuList) { item ->
//itemContent = {
                            //DisplayoneCarditem(menuItem = it)
                            Card(

                                modifier = Modifier
                                    .shadow(shape = RoundedCornerShape(16.dp), elevation = 10.02.dp)
                                    .padding(bottom = 5.dp)
                                    .fillMaxWidth()
                                    .clickable(onClick = {}),
                                shape = RoundedCornerShape(corner = CornerSize(16.dp)),

                                ) {

                                Row(
                                    modifier = Modifier
                                        .background(color = AppTheme.colors.background)
                                        .height(IntrinsicSize.Min)
                                ) {
                                     CardItemImage(item.item_image)

                                    Column(
                                        modifier = Modifier
                                            .padding(1.dp)
                                            .align(Alignment.CenterVertically)
                                            .width(IntrinsicSize.Max)

                                    ) {
                                        Text(
                                            text = " ${item.item_name}",
                                            style = TextStyle(
                                                fontStyle = FontStyle.Italic,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = blue
                                            )
                                        )
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(3.dp),
                                        ) {
                                            //Spacer(modifier = Modifier.size(30.dp))
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.padding(3.dp),
                                            ) {
                                                Text(
                                                    text = "$",
                                                    style = TextStyle(
                                                        fontStyle = FontStyle.Italic,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = orange
                                                    )
                                                )
                                                Text(
                                                    text = " ${item.item_price}",
                                                    style = TextStyle(
                                                        fontStyle = FontStyle.Italic,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 16.sp,
                                                        color = orange
                                                    )
                                                )
                                            }
                                        }
//
                                        var quantity by remember { mutableStateOf(1) }
                                        Row(
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .padding(3.dp),
                                        ) {
                                            CircularAddButton(
                                                onClick = {
                                                    quantity++
                                                    val api = RetrofitInstance.api
                                                    coroutineScope.launch {
                                                        val updateQuantityRequest =
                                                            UpdateQuantityRequest(
                                                                orderItemId = item.order_item_id,
                                                                quantity = quantity
                                                            )
                                                        api.updateOrderQuantity(
                                                            updateQuantityRequest
                                                        ).enqueue(object :
                                                            retrofit2.Callback<ApiResponse> {
                                                            override fun onResponse(
                                                                call: retrofit2.Call<ApiResponse>,
                                                                response: retrofit2.Response<ApiResponse>
                                                            ) {
                                                                if (response.isSuccessful) {
                                                                    println("Success: ${response.body()?.message}")
                                                                } else {
                                                                    println(
                                                                        "Error: ${
                                                                            response.errorBody()
                                                                                ?.string()
                                                                        }"
                                                                    )
                                                                }
                                                            }


                                                            override fun onFailure(
                                                                call: retrofit2.Call<ApiResponse>,
                                                                t: Throwable
                                                            ) {
                                                                println("Failure: ${t.message}")
                                                            }
                                                        })
                                                        try{val response = api.getTotal(4)
                                                            prixtotal.value = response.total
                                                            Log.e("RestaurantMenuScreen", ": ${response}")
                                                        } catch (e: Exception) {
                                                            // Gérer les erreurs
                                                            println("Failed to update total: ${e.message}")
                                                        }
                                                    }

                                                },
                                                content = "+",
                                                contentColor = gray,
                                                buttonColor = gray2,
                                                size = 40,
                                                contentsize = 25
                                            )
                                            Spacer(modifier = Modifier.size(4.dp))
                                            Text(text = "$quantity", style = TextStyle(color = blue)

                                            )
                                            Spacer(modifier = Modifier.size(4.dp))
                                            CircularAddButton(
                                                onClick = { if (quantity > 1) {
                                                    val api = RetrofitInstance.api
                                                    coroutineScope.launch {
                                                            val updateQuantityRequest =
                                                                UpdateQuantityRequest(
                                                                    orderItemId = item.order_item_id,
                                                                    quantity = quantity
                                                                )
                                                            api.updateOrderQuantity(
                                                                updateQuantityRequest
                                                            ).enqueue(object :
                                                                retrofit2.Callback<ApiResponse> {
                                                                override fun onResponse(
                                                                    call: retrofit2.Call<ApiResponse>,
                                                                    response: retrofit2.Response<ApiResponse>
                                                                ) {
                                                                    if (response.isSuccessful) {
                                                                        println("Success: ${response.body()?.message}")
                                                                    } else {
                                                                        println(
                                                                            "Error: ${
                                                                                response.errorBody()
                                                                                    ?.string()
                                                                            }"
                                                                        )
                                                                    }
                                                                }


                                                                override fun onFailure(
                                                                    call: retrofit2.Call<ApiResponse>,
                                                                    t: Throwable
                                                                ) {
                                                                    println("Failure: ${t.message}")
                                                                }
                                                            })
                                                        try{val response = api.getTotal(4)
                                                        prixtotal.value = response.total
                                                        Log.e("RestaurantMenuScreen", ": ${response}")
                                                    } catch (e: Exception) {
                                                        // Gérer les erreurs
                                                        println("Failed to update total: ${e.message}")
                                                    }
                                                        }

                                                    quantity--


                                                 }
                                                },
                                                content = "-",
                                                contentColor = gray,
                                                buttonColor = gray2,
                                                size = 40,
                                                contentsize = 30
                                            )
                                            // Text(text = DialogScreen().inputText)
                                        }



                                    }
                                    Column(

                                        horizontalAlignment = Alignment.End,
                                        verticalArrangement = Arrangement.SpaceAround,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .width(IntrinsicSize.Max)
                                            .padding(horizontal = 2.dp),
                                    ) {
                                        //Spacer(modifier = Modifier.size(6.dp))


                                        //DialogScreen()
                                        var showDialog = remember { mutableStateOf(false) }
                                        var additionalNote = remember { mutableStateOf(item.item_special_notes?:"") }

                                        FloatingActionButton(
                                            onClick = { showDialog.value = true
                                            } ,
                                            containerColor = gray2,
                                            contentColor = gray,
                                            modifier = Modifier.size(40.dp)
                                        ) {
                                            Icon(Icons.Default.Edit, contentDescription = "Add")
                                        }
                                        EditableTextDialog(
                                            initialText = additionalNote.value,
                                            showDialog = showDialog.value,
                                            onDismiss = { showDialog.value = false },
                                            onConfirm = { text ->
                                                additionalNote.value =  text
                                                showDialog.value = false
                                                Log.d("hellllooo${item.item_id} ", "Error: ${menuList}")
                                                val updateNotesRequest = UpdateNotesRequest(orderItemId = item.order_item_id, specialNotes = additionalNote.value)
                                                api.updateOrderNotes(updateNotesRequest).enqueue(object : retrofit2.Callback<ApiResponse> {
                                                    override fun onResponse(call: retrofit2.Call<ApiResponse>, response: retrofit2.Response<ApiResponse>) {
                                                        if (response.isSuccessful) {
                                                            println("Success: ${response.body()?.message}")
                                                        } else {
                                                            println("Error: ${response.errorBody()?.string()}")
                                                        }
                                                    }

                                                    override fun onFailure(call: retrofit2.Call<ApiResponse>, t: Throwable) {
                                                        println("Failure: ${t.message}")
                                                    }
                                                })
                                            }
                                        )
                                        FloatingActionButton(
                                            onClick = {

                                                coroutineScope.launch {
                                                    try {
                                                        val apiResponse = api.removeItemFromCart(item.order_item_id)
                                                        // Handle the successful response
                                                        println(apiResponse.message)
                                                    } catch (e: Exception) {
                                                        // Handle errors
                                                        println("Error: ${e.message}")
                                                    }
                                                    try{val response = api.getTotal(4)
                                                        prixtotal.value = response.total
                                                        Log.e("RestaurantMenuScreen", ": ${response}")
                                                    } catch (e: Exception) {
                                                        // Gérer les erreurs
                                                        println("Failed to update total: ${e.message}")
                                                    }
                                                }

                                                menuList = menuList.filter { it.item_id != item.item_id }

                                            },
                                            containerColor = gray2,
                                            contentColor = blue,
                                            modifier = Modifier.size(40.dp)
                                        ) {
                                            Icon(Icons.Default.Delete, contentDescription = "")
                                        }

                                    }


                                }

                            }


                        }



                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = white,
                    contentColor = white.copy(0.5f),

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    //.padding(WindowInsets.navigationBars.asPaddingValues())
                    .padding(bottom = 90.dp, top = 30.dp)
                    .background(white.copy(0.1f),)
                        ,
                shape = RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp)

            )
            {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(bottom = 30.dp, top = 15.dp)
                        .fillMaxWidth()
                        .background(white.copy(0.7f),),

                ) {


                    Column(
                        modifier = Modifier.background(white.copy(0.7f),)
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(3.dp)
                                .background(white.copy(0.7f),),
                        ) {
                            Text(
                                text = "Delivery fees : ",
                                style = TextStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = (16).sp,
                                    color = orange
                                )
                            )


                            Text(
                                text = "50.0$",
                                style = TextStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = blue
                                )
                            )
                        }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(3.dp)
                            .background(white.copy(0.7f),),
                    ) {
                        Text(text = "Total price : ", style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = orange
                            ))
                        coroutineScope.launch { try{val response = api.getTotal(4)
                            prixtotal.value = response.total
                            Log.e("RestaurantMenuScreen", ": ${response}")
                        } catch (e: Exception) {
                            // Gérer les erreurs
                            println("Failed to update total: ${e.message}")
                        } }
                        Text(
                            text = "${prixtotal.value}$",
                            style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = blue
                            )
                        )
                    }}



                    ExtendedButton(
                        content = "Checkout",
                        imageVector = Icons.Filled.ShoppingCart,
                        onClick = {
                      })

               }
            }

        }
    }

}




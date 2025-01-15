package com.example.myapplication.ui.theme

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talabi.Composants.TopBar
import com.example.talabi.Destination
import com.example.talabi.R
import com.example.talabi.SharedViewModel
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.gray2
import com.example.talabi.ui.theme.white
import kotlinx.coroutines.launch

import com.example.talabi.api.RetrofitInstance
import com.example.talabi.api.RetrofitInstance.api
import com.example.talabi.api.SimpleApi
import com.example.talabi.data.ApiResponse
import com.example.talabi.data.UpdateOrderRequestt
import com.example.talabi.data.UpdateOrderResponsee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun DisplayLieuPage(navController: NavHostController, orderId: Int) {
fun DisplayLieuPage(navController: NavHostController, sharedViewModel: SharedViewModel) {
       val coroutineScope = rememberCoroutineScope()
    val orderid = sharedViewModel.orderId

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.img_5),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )
                TopBar(
                    content = "Delivery address",
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    onClick = { navController.navigate(Destination.PayementandAddress.route) },
                    padding = 20,
                    contentColor = white
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                val addressText = remember { mutableStateOf("") }
                val specialInstructions = remember { mutableStateOf("") }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "",
                        tint = gray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Delivery address",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Enter your address",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                TextField(
                    value = addressText.value,
                    onValueChange = { addressText.value = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = gray2,
                        unfocusedIndicatorColor = gray,
                        cursorColor = gray,
                        focusedLabelColor = gray
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("City EL Shouhada")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Add special instructions :",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                TextField(
                    value = specialInstructions.value,
                    onValueChange = { specialInstructions.value = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = gray2,
                        unfocusedIndicatorColor = gray,
                        cursorColor = gray,
                        focusedLabelColor = gray
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("...")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                                val updateRequest = UpdateOrderRequestt(
                                    delivery_address = addressText.value,
                                    delivery_notes = specialInstructions.value
                                )
                            api.updateOrder(orderid,updateRequest).enqueue(object : retrofit2.Callback<UpdateOrderResponsee> {
                                override fun onResponse(
                                    call: Call<UpdateOrderResponsee>,
                                    response: Response<UpdateOrderResponsee>
                                ) {
                                    if (response.isSuccessful) {
                                        println("Success: ${response.body()?.message}")
                                    } else {
                                        println("Error: ${response.errorBody()?.string()}")
                                    }
                                }

                                override fun onFailure(
                                    call: Call<UpdateOrderResponsee>,
                                    t: Throwable
                                ) {
                                    println("Failure: ${t.message}")
                                }
                            })
                        navController.navigate(Destination.PayementandAddress.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFE8C00) // Custom color
                    )
                ) {
                    Text(text = "Confirm address", fontSize = 16.sp)
                }
            }
        }
    }
}

package com.example.talabi.ui.theme

import android.annotation.SuppressLint
import android.os.Build
import android.text.format.DateUtils.formatDateTime
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.talabi.Composants.CallButton
import com.example.talabi.Composants.TopBar
import com.example.talabi.R
import com.example.talabi.SharedViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.data.NotificationDto
import com.example.talabi.data.OrderStatus
import com.example.talabi.data.Orders
import kotlinx.coroutines.delay
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotifItem(notif: NotificationDto) {

    val formattedDate = try {
        // Parse the datetime string and format it
        val parsedDate = LocalDateTime.parse(notif.updatedAt, DateTimeFormatter.ISO_DATE_TIME)
        parsedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))
    } catch (e: Exception) {
        Log.e("NotifItem", "Error parsing date: ${e.localizedMessage}")
        notif.updatedAt // Fallback to the raw string if parsing fails
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            // Conditionally set the image resource based on the notification status
            val imageResource = when (notif.status) {
                "pending", "canceled" -> R.drawable.modificon
                "delivered", "on the way" -> R.drawable.img_21
                "preparing" -> R.drawable.img_20
                else -> R.drawable.img_20 // Default image if no condition matches
            }
            Row() {

                Image(
                    painter = painterResource(imageResource),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Row {
                        Text(
                            text = "Your Order is :",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = notif.status,
                            fontSize = 18.sp,
                            color = orange,
                            fontWeight = FontWeight.Bold
                            // Adjusted font size
                        )

                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    // Display the status with a font size of 18

                    Spacer(modifier = Modifier.height(4.dp))

                    // Format the updatedAt date before displaying
                    // val formattedDate = formatDateTime(notif.updatedAt)
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = " $formattedDate")

                    }


                }
            }
            if (notif.status == "delivered"){
                CallButton(phoneNumber = "1234567890")

        }

    }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotifListt(sharedViewModel: SharedViewModel) {
    val orderId = sharedViewModel.orderId
    val isOrderConfirmed = sharedViewModel.orderConfirmed
    var notifications by remember { mutableStateOf<List<NotificationDto>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    var previousStatus by remember { mutableStateOf<String?>(null) }
    var order = remember {
        mutableStateOf(
            Orders(
                id = 1,
                user_id = 1,
                restaurant_id = 1,
                delivery_notes = "",
                delivery_address = "",
                total_price = 0.0,
                created_at = "",
                updated_at = "",
                status = "",
            )
        )
    }


    fun addNotification(newStatus: String) {
        val newNotification = NotificationDto(
            orderId = order.value.id!!,
            status = newStatus,
            updatedAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        )

        // Avoid duplicates
        if (!notifications.any { it.status == newStatus && it.orderId == order.value.id }) {
            notifications = notifications + newNotification
        }
    }

    // Utiliser une variable pour contrôler la boucle
    var isActive by remember { mutableStateOf(true) }

    // Nettoyer la coroutine quand le composant est détruit
    DisposableEffect(Unit) {
        onDispose {
            isActive = false
        }
    }


    LaunchedEffect(isOrderConfirmed) {
        while (isActive) {
            try {
                val response = RetrofitInstance.api.getOrderById(orderId)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        // Check if the status has changed
                        if (previousStatus != responseBody.status) {
                            addNotification(responseBody.status)
                        }
                        previousStatus = responseBody.status
                        order.value = responseBody
                    }
                }
            } catch (e: Exception) {
                Log.e("Order", "Error fetching order: ${e.localizedMessage}")
            }

            delay(5000)
        }
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top bar est toujours affiché
        TopBar(
            content = "Notifications",
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            onClick = {}
        )


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 110.dp)
            ) {
                item {
                    Text(
                        text = "Status Updates",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                items(notifications.reversed()) { notif ->
                    NotifItem(notif)
                    HorizontalDivider(modifier = Modifier
                        .size(500.dp)
                        .padding(8.dp))
                }
            }

    }
}
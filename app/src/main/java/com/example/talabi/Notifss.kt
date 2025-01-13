package com.example.talabi.ui.theme

import android.annotation.SuppressLint
import android.os.Build
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
import com.example.talabi.Composants.TopBar
import com.example.talabi.R
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.data.NotificationDto
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.TextStyle

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotifListt() {
    val userId = 1 // Set userId to 1 for testing
    var notifications by remember { mutableStateOf<List<NotificationDto>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    // Fetch notifications from the backend
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitInstance.api.getNotifications(userId)
                if (response.isSuccessful) {
                    notifications = response.body() ?: emptyList()
                } else {
                    // Log error if needed
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
            }
        }
    }

    // Group notifications by date
    val groupedNotifs = groupNotificationsByDate(notifications)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TopBar(
                content = "Notifications",
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onClick = {}
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 110.dp)
        ) {
            groupedNotifs.forEach { (group, notifications) ->
                // Section title
                item {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = group,
                            fontSize = 18.sp,
                            color = Color(0xFF3F3D56),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .fillMaxWidth()
                        )
                        Divider(
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                                .fillMaxWidth(),
                            thickness = 1.dp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // Notifications in the group
                items(notifications) { notif ->
                    NotifItem(notif)
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotifItem(notif: NotificationDto) {
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
                "pending", "cancelled" -> R.drawable.modificon
                "on_the_way", "delivered" -> R.drawable.img_21
                "preparing" -> R.drawable.img_20
                else -> R.drawable.profile // Default image if no condition matches
            }

            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = "Your Order State",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Display the status with a font size of 18
                Text(
                    text = notif.status,
                    fontSize = 18.sp // Adjusted font size
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Format the updatedAt date before displaying
                val formattedDate = formatDateTime(notif.updatedAt)
                Text(text = formattedDate)
            }
        }
    }
}


// Helper function to format the full date-time string
@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTime(dateTime: String): String {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val dateTimeObj = ZonedDateTime.parse(dateTime, formatter)

    // Format to a more user-friendly string
    val formattedDate = dateTimeObj.format(DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' h:mm a"))
    return formattedDate
}

@RequiresApi(Build.VERSION_CODES.O)
fun groupNotificationsByDate(notifications: List<NotificationDto>): Map<String, List<NotificationDto>> {
    val formatter = DateTimeFormatter.ISO_DATE_TIME // Use the built-in ISO_DATE_TIME formatter
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)

    return notifications.groupBy { notif ->
        val notificationDate = LocalDateTime.parse(notif.updatedAt, formatter) // Parse the full date-time string
        when {
            notificationDate.toLocalDate() == today -> "Aujourd'hui"
            notificationDate.toLocalDate() == yesterday -> "Hier"
            notificationDate.toLocalDate().isAfter(today.minusWeeks(1)) -> "Cette semaine"
            else -> "Plus ancien"
        }
    }
}
//package com.example.talabi.ui.theme
//
//
//import android.annotation.SuppressLint
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
//import androidx.compose.material.icons.filled.KeyboardArrowLeft
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.talabi.Composants.TopBar
//import com.example.talabi.R
//import com.example.talabi.data.NotifData
//import com.example.talabi.data.Notification
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter
//
//
//@SuppressLint("SuspiciousIndentation")
//@RequiresApi(Build.VERSION_CODES.O)
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NotifListt() {
//    val notifs = remember { mutableStateListOf(*NotifData.notifications.toTypedArray()) }
//
//    // Groupe notifications par periodes
//    val groupedNotifs = groupNotificationsByDate(notifs)
//
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//
//        ) {
//            // En-tête
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
////                Image(
////                    painter = painterResource(R.drawable.img_11),
////                    contentDescription = null,
////                    modifier = Modifier.size(44.dp)
////                )
//              TopBar(content = "Notifications", imageVector =  Icons.AutoMirrored.Filled.KeyboardArrowLeft, onClick = {})
//
//
////                Spacer(modifier = Modifier.width(80.dp))
////                Text(
////                    text = "Notifications",
////                    fontSize = 20.sp,
////                    fontWeight = FontWeight.Bold
////                )
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//            Spacer(modifier = Modifier.width(8.dp))
//
//
//            LazyColumn(
//                modifier = Modifier.fillMaxSize().padding(bottom=110.dp),
//
//                //contentPadding = PaddingValues(16.dp)
//            ) {
//
//                groupedNotifs.forEach { (group, notifications) ->
//                    // Section title
//                    item {
//                        Row {
//                            Spacer(modifier = Modifier.width(8.dp))
//                            Text(
//                                text = group,
//                                fontSize = 18.sp,
//                                color = Color(0xFF3F3D56),
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier
//                                    .padding(vertical = 4.dp)
//                                    .fillMaxWidth()
//                            )
//                            Divider(
//                                modifier = Modifier
//                                    .padding(vertical = 2.dp)
//                                    .fillMaxWidth(),
//                                thickness = 1.dp,
//                                color = Color.Gray
//                            )
//                            Spacer(modifier = Modifier.height(8.dp))
//                        }
//                    }
//
//                    // Notifications de la section
//                    items(notifications) { notif ->
//                        NotifItem(notif) { clickedNotif ->
//                            val index = notifs.indexOf(clickedNotif)
//                            if (index != -1) {
//                                notifs[index] = clickedNotif.copy(isOpened = true)
//                            }
//                        }
//                    }
//                }
//
//
//            }
//        }
//    }
//
//
//@Composable
//fun NotifItem(notif: Notification, onNotifClick: (Notification) -> Unit) {
//    val screenWidthDp: Dp = with(LocalConfiguration.current) {
//        screenWidthDp.dp
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 2.dp)
//            .background(if (!notif.isOpened) Color(0xFFB3B1B1) else Color.Transparent) // Jaune si non ouvert
//            .clickable { onNotifClick(notif) }
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(8.dp)
//        ) {
//            Image(
//                painter = painterResource(notif.img),
//                contentDescription = null,
//                modifier = Modifier.size(32.dp),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Column {
//                Text(
//                    text = notif.nom,
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(text = notif.timee)
//            }
//        }
//        Spacer(modifier = Modifier.height(4.dp))
//       /* Divider(
//            modifier = Modifier
//                .padding(vertical = 2.dp)
//                .fillMaxWidth(),
//            thickness = 1.dp,
//            color = Color.Black
//        )*/
//
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//fun groupNotificationsByDate(notifications: List<Notification>): Map<String, List<Notification>> {
//    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//    val today = LocalDate.now()
//    val yesterday = today.minusDays(1)
//
//    return notifications.groupBy { notif ->
//        val notificationDate = LocalDate.parse(notif.timee, formatter)
//        when {
//            notificationDate == today -> "Aujourd'hui"
//            notificationDate == yesterday -> "Hier"
//            notificationDate.isAfter(today.minusWeeks(1)) -> "Cette semaine"
//            else -> "Plus ancien"
//        }
//    }
//}
//
//
//

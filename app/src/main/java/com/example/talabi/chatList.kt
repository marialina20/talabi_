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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import com.example.talabi.data.ChatData
import com.example.talabi.data.ChatData.chats
import com.example.talabi.data.ChatList
import com.example.talabi.data.NotifData
import com.example.talabi.data.Notification
import com.example.talabi.data.menuItems
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatListt() {
    val chatts = remember { mutableStateListOf(*ChatData.chats.toTypedArray()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TopBar(content = "Chat List", imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, onClick = {})
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 110.dp)
        ) {
            items(
                items = chatts,
                key = { it.nom } // Assuming `nom` is unique
            ) { chat ->
                ChatItem(chat = chat, onChatClick = {
                    // Handle chat click
                })
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
            }
        }
    }
}


@Composable
fun ChatItem(chat: ChatList, onChatClick: (ChatList) -> Unit) {
                        val screenWidthDp: Dp = with(LocalConfiguration.current) {
                            screenWidthDp.dp
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .background(if (!chat.isOpened) Color(0xFFB3B1B1) else Color.Transparent) // Jaune si non ouvert
                                .clickable { onChatClick(chat) }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(chat.img),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = chat.nom,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = chat.timee)
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            /* Divider(
             modifier = Modifier
                 .padding(vertical = 2.dp)
                 .fillMaxWidth(),
             thickness = 1.dp,
             color = Color.Black
         )*/

                        }
                    }






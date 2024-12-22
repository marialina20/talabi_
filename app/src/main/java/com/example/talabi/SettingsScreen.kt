@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.talabi.data

import androidx.compose.foundation.background
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.talabi.OptionItem
import com.example.talabi.R


@Composable
fun SettingsScreen(
    navController: NavController,
    onNavigateToLangue: () -> Unit,
    onNavigateToNotifs: () -> Unit,
    onLogout: () -> Unit,
    onNavigateToSignIn: () -> Unit, // Action for logout navigation
    onNavigateToSec: () -> Unit,
    onNavigateToAide: () -> Unit,
    onNavigateToConfi: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Settings",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000080)
                        )

                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))







            Spacer(modifier = Modifier.height(24.dp))

            // Options List
            OptionItem(title = "Language", onClick = onNavigateToLangue, icon = R.drawable.langue)
            Divider(color = Color.LightGray, thickness = 1.dp)
            OptionItem(title = "Privacy and Security", onClick = onNavigateToConfi, icon = R.drawable.confi)
            Divider(color = Color.LightGray, thickness = 1.dp)
            OptionItem(title = "Notifications", onClick = onNavigateToNotifs, icon = R.drawable.notif)
            Divider(color = Color.LightGray, thickness = 1.dp)
            OptionItem(title = "Help and Support", onClick = onNavigateToAide, icon = R.drawable.aide)
            Divider(color = Color.LightGray, thickness = 1.dp)
            OptionItem(title = "Legal Section", onClick = onNavigateToSec, icon = R.drawable.sec)
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}

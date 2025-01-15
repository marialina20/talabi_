@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.talabi

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.data.User
import com.example.talabi.ui.theme.orange
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen(
    navController: NavController,
    onNavigateToFavoris: () -> Unit,
    onNavigateToParametres: () -> Unit,
    onNavigateToPaiement: () -> Unit,
    onLogout: () -> Unit,
    onNavigateToSignIn: () -> Unit, // Action for logout navigation
    onEditProfile: () -> Unit, // Action for editing profile
    sharedViewModel: SharedViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val useridd = sharedViewModel.UserIdd

    val user = remember { mutableStateOf(
        User(
            id = 1,
            name ="",
            email ="",
            phone ="",
            address ="",
            profilePicture = "",
            password = "",
        )
    ) }
    LaunchedEffect(Unit) {

        coroutineScope.launch {
            try {
                val response = RetrofitInstance.api.getUser(useridd)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        user.value = responseBody  // Assign the list of restaurants to the state
                        Log.d("user", "Data fetched successfully: $user")
                    } else {
                        Log.e("user", "Empty response body")
                    }
                } else {
                    Log.e("user", "Error fetching data: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("user", "Error: ${e.localizedMessage}")
            }
        }
    }
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
                            text = "Profile",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000080)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.modificon),
                            contentDescription = "Edit Profile",
                            modifier = Modifier
                                .size(50.dp)
                                .background(
                                    color = Color.Gray.copy(alpha = 0.1f),
                                    shape = CircleShape
                                )
                                .clickable{ }
                                .padding(14.dp),
                            tint = Color.Unspecified
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

            // Profile Image
            Box(
                modifier = Modifier
                    .size(120.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pfp),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color(0xFFF1F1F1), shape = CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // User Info
            Text(
                text = user.value.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000)
            )
            Text(
                text = user.value.email,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Stats Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(title = "Delivery", value = "18", color = orange)
                StatItem(title = "Favoris", value = "43", color = orange)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Options List
            OptionItem(title = "Favorites", onClick = onNavigateToFavoris, icon = R.drawable.favorisicon)
            Divider(color = Color.LightGray, thickness = 1.dp)
            OptionItem(title = "Settings", onClick = onNavigateToParametres, icon = R.drawable.prmtricon)
            Divider(color = Color.LightGray, thickness = 1.dp)
            OptionItem(title = "Payment", onClick = onNavigateToPaiement, icon = R.drawable.paieicon)
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Logout
            Text(
                text = "Logout",
                modifier = Modifier
                    .clickable(onClick = onNavigateToSignIn)
                    .padding(vertical = 12.dp),
                color = Color.Gray,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun StatItem(title: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun OptionItem(title: String, onClick: () -> Unit, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(Color(0xFFF9F9F9))
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color(0xFF000000),
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(id = R.drawable.adroiteicon),
            contentDescription = null,
            modifier = Modifier
                .size(12.dp)
                .clickable(onClick = onClick),
            tint = Color.Gray
        )
    }
}



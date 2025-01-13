package com.example.talabi
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap

import androidx.compose.ui.text.font.FontWeight

import androidx.navigation.NavController

import com.example.talabi.ui.theme.orange

import androidx.compose.runtime.remember

import java.io.ByteArrayOutputStream
import android.util.Base64
import android.util.Log
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.data.MenuItem
import com.example.talabi.data.User
import com.example.talabi.data.UserProfileRequest
import io.ktor.http.ContentDisposition.Companion.File
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
   // viewModel: MainViewModel // Replaced UserProfileViewModel with MainViewModel
) {
//    var firstName by remember { mutableStateOf("Ahmed") }
//    var lastName by remember { mutableStateOf("Maamar") }
//    var phoneNumber by remember { mutableStateOf("+213 01758-000666") }
//    var email by remember { mutableStateOf("maamar_ahmed@gmail.com") }

    var profileImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                // Convert Uri to Bitmap
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                profileImageBitmap = bitmap
            }
        }
    )
    val coroutineScope = rememberCoroutineScope()

    var user = remember { mutableStateOf(
       User(
            id = 1,
          name="",
           email="",
           phone="",
           address="",
           profilePicture = "",
           password = "",
        )
    ) }
    LaunchedEffect(Unit) {

        coroutineScope.launch {
            try {
                val response = RetrofitInstance.api.getUser(1)
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
    val userId = 1 // Replace with the actual user id from ViewModel or other sources

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile", fontWeight = FontWeight.Bold, color = Color(0xFF000080)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            // Create a UserProfileRequest with the data
                            val profilePictureBase64 = profileImageBitmap?.let { encodeBitmapToBase64(it) }
                            val userProfileRequest = UserProfileRequest(
                                firstName = user.value.name,
                                phoneNumber = user.value.phone,
                                email = user.value.email,
                                profilePicture = user.value.profilePicture
                            )

//                            // Call MainViewModel function to send this data to backend
//                            viewModel.updateUserProfile(userId, userProfileRequest) // Use MainViewModel's method
                            navController.navigateUp()
                        }
                    ) {
                        Text("Save", color = orange, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                    .clickable(onClick = { launcher.launch("image/*") }),
                contentAlignment = Alignment.Center
            ) {
                // Display the profile image if it is selected
                if (profileImageBitmap != null) {
                    Image(
                        bitmap = profileImageBitmap!!.asImageBitmap(),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(100.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.pfp),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(50.dp),
                        tint = Color.Unspecified
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Change Profile Picture",
                fontSize = 14.sp,
                color = orange,
                modifier = Modifier.clickable(onClick = { launcher.launch("image/*") })
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Name",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
                OutlinedTextField(
                    value = user.value.name,
                    onValueChange = {
                        user.value = user.value.copy(name = it)
                        Log.d("user", "Updated name: ${user.value.name}")
                    },

                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "Valid",
                            tint = orange
                        )
                    }
                )
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Phone number",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
                OutlinedTextField(
                    value = user.value.phone,
                    onValueChange = {
                        user.value = user.value.copy(phone = it)
                        Log.d("user", "Updated phone number: ${user.value.phone}")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "Valid",
                            tint = orange
                        )
                    }
                )
            }

//            EditableField(
//                label = "Email",
//                value = user.email,
//                onValueChange = { user.email = it },
//                keyboardType = KeyboardType.Email
//            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Email",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
                OutlinedTextField(
                    value = user.value.email,
                    onValueChange = {
                        user.value = user.value.copy(email = it)
                        Log.d("user", "Updated name: ${user.value.email}")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "Valid",
                            tint = orange
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = {
                    val profilePictureBase64 = profileImageBitmap?.let { encodeBitmapToBase64(it) }
                    val userProfileRequestt = UserProfileRequest(
                        firstName = user.value.name,

                        phoneNumber = user.value.phone,
                        email = user.value.email,
                        profilePicture = profilePictureBase64
                    )
                        coroutineScope.launch {
                            try {
                                val response = RetrofitInstance.api.updateUserProfile(1,userProfileRequestt.firstName.toRequestBody("text/plain".toMediaTypeOrNull()),userProfileRequestt.email.toRequestBody("text/plain".toMediaTypeOrNull()))
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



                    // Send data to MainViewModel


                    // Call MainViewModel function to send this data to backend
//                    viewModel.updateUserProfile(userId, userProfileRequest) // Use MainViewModel's method
                    navController.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(containerColor = orange),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}


fun encodeBitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}
//
//@Composable
//fun EditableField(
//    label: String,
//    value: String,
//    onValueChange: String,
//    keyboardType: KeyboardType = KeyboardType.Text
//) {
//    Column(modifier = Modifier.fillMaxWidth()) {
//        Text(
//            text = label,
//            fontSize = 14.sp,
//            color = MaterialTheme.colorScheme.onSurfaceVariant,
//            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
//        )
//        OutlinedTextField(
//            value = value,
//            onValueChange = { onValueChange=it },
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
//            trailingIcon = {
//                Icon(
//                    Icons.Filled.Check,
//                    contentDescription = "Valid",
//                    tint = orange
//                )
//            }
//        )
//    }
//}


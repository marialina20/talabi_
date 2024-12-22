package com.example.talabi
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.graphics.graphicsLayer
import com.example.talabi.ui.theme.orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController
) {
    var firstName by remember { mutableStateOf("Ahmed") }
    var lastName by remember { mutableStateOf("Maamar") }
    var phoneNumber by remember { mutableStateOf("+213 01758-000666") }
    var email by remember { mutableStateOf("maamar_ahmed@gmail.com") }

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
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                key = "updatedProfile",
                                value = mapOf(
                                    "firstName" to firstName,
                                    "lastName" to lastName,
                                    "phoneNumber" to phoneNumber,
                                    "email" to email
                                )
                            )
                            navController.navigateUp()
                        }
                    ) {
                        Text("Save", color = orange, fontWeight = FontWeight.Bold,fontSize = 12.sp,)
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
                    ) // Use shape instead of clip
                    .clickable(onClick = { launcher.launch("image/*") }),
                contentAlignment = Alignment.Center
            ) {
                // Display the profile image if it is selected
                if (profileImageBitmap != null) {
                    Image(
                        bitmap = profileImageBitmap!!.asImageBitmap(),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .graphicsLayer {
                                // Add transformations if necessary, for example, scaling, rotation, etc.
                            }
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

            // Editable Fields
            EditableField(
                label = "First Name",
                value = firstName,
                onValueChange = { firstName = it }
            )
            EditableField(
                label = "Last Name",
                value = lastName,
                onValueChange = { lastName = it }
            )
            EditableField(
                label = "Phone Number",
                value = phoneNumber,
                onValueChange = { phoneNumber = it }
            )
            EditableField(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        key = "updatedProfile",
                        value = mapOf(
                            "firstName" to firstName,
                            "lastName" to lastName,
                            "phoneNumber" to phoneNumber,
                            "email" to email
                        )
                    )
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

@Composable
fun EditableField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Valid",
                    tint = orange
                )
            }
        )
    }
}

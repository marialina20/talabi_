package com.example.talabi

import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.clickable
import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.shape.RoundedCornerShape
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import androidx.compose.ui.platform.LocalContext
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier, onNavigateBack: () -> Unit)  {
    val context = LocalContext.current
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneNumberError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    // Google Sign-In setup
    val RC_SIGN_IN = 9001
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("YOUR_WEB_CLIENT_ID") // Replace with your Web Client ID from Firebase Console
        .requestEmail()
        .build()

    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    // Google Sign-In result handling
    val signInResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                // Handle successful Google Sign-In
                val username = account?.displayName
                val email = account?.email
                val idToken = account?.idToken
                // You can now use these details for your app's authentication
            } catch (e: ApiException) {
                // Handle sign-in failure
                Log.w("Google Sign-In", "Sign-in failed", e)
            }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Makes the page scrollable
            .padding(16.dp),
        verticalArrangement = Arrangement.Top, // Avoid forcing items to be centered
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo1), // Replace with your actual logo resource
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp) // Adjust logo size (reduce if needed)
                .padding(bottom = 8.dp) // Reduce padding under the logo
        )

        // Title
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Left
        )

        // Subtitle
        Text(
            text = "Please fill in the details",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(top = 4.dp) // Reduce top padding
        )

        // Optional small spacer to separate title from form
        Spacer(modifier = Modifier.height(8.dp))

        // Username field
        OutlinedTextField(
            value = username.value,
            onValueChange = {
                username.value = it
                if (it.isNotEmpty()) usernameError = null
            },
            label = { Text("Username") },
            modifier = Modifier
                .padding(vertical = 8.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            isError = usernameError != null
        )
        if (usernameError != null) {
            Text(
                text = usernameError!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Email field
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) emailError = null
            },
            label = { Text("Email") },
            modifier = Modifier
                .padding(vertical = 6.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = emailError != null
        )
        if (emailError != null) {
            Text(
                text = emailError!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Phone number field
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = {
                phoneNumber.value = it
                if (it.isNotEmpty() && android.util.Patterns.PHONE.matcher(it).matches()) phoneNumberError = null
            },
            label = { Text("Phone Number") },
            modifier = Modifier
                .padding(vertical = 6.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            isError = phoneNumberError != null
        )
        if (phoneNumberError != null) {
            Text(
                text = phoneNumberError!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Password field
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
                if (it.length >= 8) passwordError = null
            },
            label = { Text("Password") },
            modifier = Modifier

                .padding(vertical = 6.dp),
            singleLine = true,
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        painter = if (passwordVisible.value) painterResource(id = R.drawable.eye_on) else painterResource(id = R.drawable.eye_off),
                        contentDescription = if (passwordVisible.value) "Hide password" else "Show password",
                        modifier = Modifier.size(22.dp)
                    )
                }
            },
            isError = passwordError != null
        )
        if (passwordError != null) {
            Text(
                text = passwordError!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Google Sign-In clickable text
        Text(
            text = "Sign Up with Google",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .clickable {
                    val signInIntent = googleSignInClient.signInIntent
                    (context as Activity).startActivityForResult(signInIntent, RC_SIGN_IN)
                }
        )

        // Sign Up button
        Button(
            onClick = {
                var isValid = true

                if (username.value.isEmpty()) {
                    usernameError = "Username is required"
                    isValid = false
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
                    emailError = "Please enter a valid email address"
                    isValid = false
                }

                if (phoneNumber.value.isEmpty() || !android.util.Patterns.PHONE.matcher(phoneNumber.value).matches()) {
                    phoneNumberError = "Please enter a valid phone number"
                    isValid = false
                }

                if (password.value.length < 8) {
                    passwordError = "Password must be at least 8 characters"
                    isValid = false
                }

                if (isValid) {
                    // Handle registration logic
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange, // Default background color
                contentColor = white, // Text color
                disabledContainerColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f) // Disabled state color
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Sign Up")
        }
        Text(
            text = "Back to Login",
            color = orange,
            modifier = Modifier.clickable { onNavigateBack() }
        )
    }
}

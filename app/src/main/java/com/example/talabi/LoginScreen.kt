package com.example.talabi

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.talabi.R
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.data.LoginRequest
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(modifier: Modifier = Modifier, sharedViewModel: SharedViewModel,onNavigateToSignUp: () -> Unit,navController: NavHostController) {
    val context = LocalContext.current

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var loginRequest by remember{ mutableStateOf(LoginRequest(email = "user@example.com", password = "password123")) }
    // Google Sign-In setup
    val coroutineScope = rememberCoroutineScope()
    val RC_SIGN_IN = 9001
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("YOUR_WEB_CLIENT_ID") // Replace with your Web Client ID
        .requestEmail()
        .build()

    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    val signInResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val email = account?.email
                val idToken = account?.idToken
                Log.d("Google Sign-In", "Successful sign-in: $email")
            } catch (e: ApiException) {
                Log.w("Google Sign-In", "Sign-in failed", e)
            }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo1), // Replace with your logo
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp).padding(bottom = 8.dp)
        )

        // Title
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Left
        )

        // Subtitle
        Text(
            text = "Please log in to continue",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(top = 4.dp) // Reduce top padding
        )


        Spacer(modifier = Modifier.height(8.dp))

        // Email field
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
                loginRequest.email=email.value
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) emailError = null
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = emailError != null
        )
        if (emailError != null) {
            Text(text = emailError!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        // Password field
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
                loginRequest.password=password.value
                if (it.length >= 8) passwordError = null

            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        painter = if (passwordVisible.value) painterResource(R.drawable.eye_on) else painterResource(R.drawable.eye_off),
                        contentDescription = if (passwordVisible.value) "Hide password" else "Show password",
                        modifier = Modifier.size(22.dp)
                    )
                }
            },
            isError = passwordError != null
        )
        if (passwordError != null) {
            Text(text = passwordError!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        // Forgot Password
        Text(
            text = "Forgot Password?",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { /* Implement navigation or action */ },
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = {
                var isValid = true
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
                    emailError = "Please enter a valid email address"
                    isValid = false
                }
                if (password.value.length < 8) {
                    passwordError = "Password must be at least 8 characters"
                    isValid = false
                }
                if (isValid) {
                    // Handle login logic
                    Log.d("Login", "Email: ${email.value}, Password: ${password.value}")
                }

            coroutineScope.launch {


                try {
                    val response = RetrofitInstance.api.login(loginRequest)
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        sharedViewModel.setUserId(response.body()!!.user!!.id)
                        println("hhhhhhhhhhhhhhhhhhhhhhhhhhh${sharedViewModel.UserIdd}")
                        if (loginResponse != null) {

                            navController.navigate(Destination.home.route)
                            sharedViewModel.setLoggedIn(true)
                            Log.d("Loginnnnnnnnnnnnnnnnnnnnnn", "Login successful: ${loginResponse.user}")
                        } else {
                            passwordError = "Please enter a valid password"
                            isValid = false
                            Log.e("Login", "Empty response body")
                        }
                    } else {

                        passwordError = "Please enter a valid password"
                        isValid = false

                        Log.e("Login", "Login failed: ${response.errorBody()?.string()}")
                    }
                } catch (e: Exception) {
                    Log.e("Login", "Error: ${e.localizedMessage}")
                }
            }},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
                contentColor = white
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Login")
        }



        // Separator line for "Or"
        Spacer(modifier = Modifier.height(16.dp))
        Divider(modifier = Modifier.width(200.dp), color = Color.Gray)
        Text(text = "Or", modifier = Modifier.padding(8.dp))

        // Sign up text: "Don't have an account? Sign up"
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(text = "Don't have an account? ")
            Text(
                text = "Sign up",
                color = orange,
                modifier = Modifier.clickable { onNavigateToSignUp() }
            )
        }

        // Google Sign-In
        Text(
            text = "Login with Google",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable {
                    val signInIntent = googleSignInClient.signInIntent
                    (context as Activity).startActivityForResult(signInIntent, RC_SIGN_IN)
                }
        )
    }
}

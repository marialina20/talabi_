package com.example.talabi
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.talabi.api.RetrofitInstance
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import retrofit2.Response
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            LoginScreen(onLogin = { email, password ->
//                loginUser(email, password)
//            })
//        }
//    }
//
//    private fun loginUser(email: String, password: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val api = RetrofitInstance.api
//                val credentials = Loginclass("ahlem@gmail.com", "ah12//()")
//                val response: Response<LoginResponse> = api.loginUser(credentials)
//
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful && response.body() != null) {
//                        val loginResponse = response.body()!!
//                        Toast.makeText(
//                            this@MainActivity,
//                            "Login successful! Token: ${loginResponse.token}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        // Save token or navigate to the next screen
//                    } else {
//                        Toast.makeText(
//                            this@MainActivity,
//                            "Login failedddddddddd: ${response.errorBody()?.string()}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        "Error: ${e.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }
//}

// package com.example.talabi

import DisplayCardItems
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.privacysandbox.tools.core.model.Type
import com.example.ahlem.CategoriesScreen
import com.example.ahlem.HomeScreen
import com.example.ahlem.MainScreeen
import com.example.ahlem.MoreRestaurantsScreen
import com.example.ahlem.RestaurantDetailsScreen
import com.example.ahlem.SearchScreen
import com.example.myapplication.ui.theme.DisplayLieuPage
import com.example.talabi.data.SettingsScreen
import com.example.talabi.ui.theme.AppTheme
import com.example.talabi.ui.theme.ChatListt
import com.example.talabi.ui.theme.MyBottomNavigation
import com.example.talabi.ui.theme.NotifListt



class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController= rememberNavController()
<<<<<<< Updated upstream
                //MainScreen()
                // DisplayItemDiscreption(menuItemid = 7, navController =navController)
                //DisplayPayementInfo(userid = 1, orderid = 1)
                // DisplayCardItems(navController)
                // DisplayRestaurantMenu(navController = )
                MyBottomNavigation()
                // MainScreeen()
=======
            // DisplayItemDiscreption(menuItemid = 7, navController =navController)
            //DisplayPayementInfo(userid = 1, orderid = 1)
               //DisplayCardItems(navController)
           // DisplayRestaurantMenu(navController = )
               // MyBottomNavigation()
                ChatListt()
>>>>>>> Stashed changes
                // DialogScreen()
                //  RatingDialogScreen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreen(navController: NavHostController,modifier:Modifier =Modifier) {
    val selectedLanguage = remember { mutableStateOf("Francais") }

    NavHost(navController=navController, startDestination= Destination.home.route

    ) {


        composable(Destination.RestaurantMenu.route) { DisplayRestaurantMenu(navController) }
        composable(Destination.FoodDescription.route) {
            DisplayItemDiscreption(menuItemid = 7, navController =navController )
        }
        composable(Destination.Card.route) { DisplayCardItems(navController) }
        composable(Destination.Notification.route) { NotifListt() }
        composable(Destination.PayementandAddress.route) { DisplayPayementInfo(userid = 1, orderid = 1,navController) }
        composable(Destination.LieuPage.route) { DisplayLieuPage(navController) }
        composable(Destination.home.route) { HomeScreen(navController) }
        composable(Destination.search.route) { SearchScreen() }
        composable(Destination.categories.route) { CategoriesScreen() }
        composable(Destination.restaurant_details.route) { RestaurantDetailsScreen() }
        composable(Destination.more.route) { MoreRestaurantsScreen() }
        composable(Destination.login.route) {
            LoginScreen(
                modifier = Modifier.fillMaxSize(),
                onNavigateToSignUp = { navController.navigate("signup") },
                // onLoginSuccess = { navController.navigate("profile") } // Navigate to Profile screen after login
            )
        }
        composable(Destination.signup.route) {
            RegistrationScreen(
                modifier = Modifier.fillMaxSize(),
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Destination.profile.route) {
            ProfileScreen(
                navController = navController,
                onNavigateToFavoris = { /* Handle navigation to Favoris */ },
                onNavigateToParametres = {navController.navigate("settings")  },
                onNavigateToPaiement = { /* Handle navigation to Paiement */ },
                onLogout = { navController.popBackStack() }, // Handle logout by popping back to login
                onNavigateToSignIn = { navController.navigate("login") }, // Navigate to login screen after logout
                onEditProfile = { navController.navigate("edit_profile") } // Navigate to EditProfile screen
            )
        }
        composable(Destination.settings.route) {
            SettingsScreen(
                navController = navController,
                onNavigateToLangue= { navController.navigate("language")  },
                onNavigateToNotifs= { /* Handle navigation to  */ },
                onLogout= { navController.popBackStack() },
                onNavigateToSignIn= {  navController.navigate("login") },
                onNavigateToSec= { /* Handle navigation to  */ },
                onNavigateToAide= { /* Handle navigation to  */ },
                onNavigateToConfi= { /* Handle navigation to  */ },
            )
        }
        composable(Destination.language.route) {
            LanguageSettingsScreen(
                navController = navController,
                selectedLanguage = selectedLanguage.value,
                onLanguageSelected = { selectedLanguage.value = it }
            )
        }

        composable("edit_profile") {
            EditProfileScreen(
                navController = navController // Pass NavController for back navigation
            )
        }
    }

}


//class MainActivity : ComponentActivity() {
//    private lateinit var viewModel: MainViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main) // Ensure the correct layout file is used
//
//        // Bind views
//        val numberEditText = findViewById<EditText>(R.id.number_editText)
//        val button = findViewById<Button>(R.id.button)
//        val textView = findViewById<TextView>(R.id.textView)
//
//
//
//
//        val repository = Repository()
//        val viewModelFactory = MainViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//
//        // Set button click listener
//        button.setOnClickListener {
//            val myNumber = numberEditText.text.toString()
//
//            if (myNumber.isNotEmpty()) {
//                // Call the ViewModel method with the input number
//                viewModel.getCustomPosts(myNumber.toIntOrNull() ?: 0)
//
//                // Observe the ViewModel response
//                viewModel.myCustomPosts.observe(this, Observer { response ->
//                    if (response.isSuccessful) {
//                        textView.text = response.body().toString() // Display successful response
//                        response.body()?.forEach {
//                            Log.d("Response", it.name)
//                            Log.d("Response", it.id.toString())
//                            Log.d("Response", it.phone)
//                            Log.d("Response", it.address)
//                            Log.d("Response", "-----------------------------")
//                        }
//                    } else {
//                       // textView.text = "Error: ${response.errorBody().toString()}" // Display error
//                        Log.d("Response", response.errorBody().toString())
//                    }
//                })
//            } else {
//               // textView.text = "Please enter a valid number"
//            }
//        }
//    }
//}
//********************************signup*****************************************88
//import androidx.compose.runtime.*
//import com.example.talabi.api.RetrofitInstance
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AppTheme {
//                RegistrationnScreen(
//                    modifier = Modifier,
//                    onNavigateBack = { finish() },
//                    onSignUp = { username, email, phone, password ->
//                        signUpUser(username, email, phone, password)
//                    }
//                )
//            }
//        }
//    }
//
//    private fun signUpUser(username: String, email: String, phone: String, password: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val api = RetrofitInstance.api
//                val newUser = Post(
//                    userId = 0, // Assign 0 if ID is auto-generated
//                    name = username,
//                    email = email,
//                    phone = phone,
//                    password = password
//                )
//                val response = api.pushPost(newUser)
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        Toast.makeText(
//                            this@MainActivity,
//                            "Sign up successful! Welcome, $username.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    } else {
//                        Toast.makeText(
//                            this@MainActivity,
//                            "Sign up failed: ${response.errorBody()?.string()}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        "Error: ${e.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }
//}
//****************************login**********************************************






//
//class MainActivity : ComponentActivity() {
//    private lateinit var viewModel: MainViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(activity_main)
//
//        val repository = Repository()
//        val viewModelFactory = MainViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//
//        button.setOnClickListener{
//            val myNumber = number_editText.text.toString()
//            viewModel.getPost2(Integer.parseInt(myNumber))
//
//            viewModel.myResponse2.observe(this, Observer{ response ->
//                if(response.isSuccessful){
//                    textView.text = response.body().toString()
//                }else{
//                    Log.d("Response", response.errorBody().toString())
//                }
//
//            })
//
//        }
//
//
//    }    }
//
//
//
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            AppTheme {
//                val navController= rememberNavController()
//                //MainScreen()
//            // DisplayItemDiscreption(menuItemid = 7, navController =navController)
//            //DisplayPayementInfo(userid = 1, orderid = 1)
//              // DisplayCardItems(navController)
//           // DisplayRestaurantMenu(navController = )
//                MyBottomNavigation()
//               // MainScreeen()
//                // DialogScreen()
//              //  RatingDialogScreen()
//            }
//        }
//    }
//}


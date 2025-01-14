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



import DisplayCardItems
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ahlem.CategoriesScreen
import com.example.ahlem.HomeScreen
import com.example.ahlem.MoreRestaurantsScreen
import com.example.ahlem.RestaurantDetailsScreen
import com.example.ahlem.SearchScreen
import com.example.myapplication.ui.theme.DisplayLieuPage
import com.example.talabi.data.SettingsScreen
import com.example.talabi.ui.theme.AppTheme
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

                //MainScreen()
                // DisplayItemDiscreption(menuItemid = 7, navController =navController)
                //DisplayPayementInfo(userid = 1, orderid = 1)
                // DisplayCardItems(navController)
                // DisplayRestaurantMenu(navController = )
                MyBottomNavigation()

            // DisplayItemDiscreption(menuItemid = 7, navController =navController)
            //DisplayPayementInfo(userid = 1, orderid = 1)
               //DisplayCardItems(navController)
           // DisplayRestaurantMenu(navController = )
               // MyBottomNavigation()

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

    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController=navController, startDestination= Destination.home.route

    ) {




        composable(
            "restaurantMenu/{restaurantId}",
            arguments = listOf(navArgument("restaurantId") { type = NavType.StringType })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
            DisplayRestaurantMenu(navController, restaurantId = restaurantId, sharedViewModel)
        }
//        composable(Destination.FoodDescription.route) {
//            DisplayItemDiscreption(menuItemid = 7, navController =navController ,sharedViewModel=sharedViewModel)
//        }
        composable(Destination.Card.route) { DisplayCardItems(navController, sharedViewModel) }
        composable(Destination.Notification.route) { NotifListt() }
        composable(Destination.PayementandAddress.route) { DisplayPayementInfo(userid = 1, orderid = 1,navController) }
//        composable(
//            route = "${Destination.LieuPage.route}/{orderId}",
//            arguments = listOf(navArgument("orderId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val orderId = backStackEntry.arguments?.getInt("orderId") ?: 0
//            DisplayLieuPage(navController, orderId)
//        }
        composable(Destination.LieuPage.route) { DisplayLieuPage(navController) }
        composable(Destination.home.route) { HomeScreen(navController) }
        composable(Destination.search.route) { SearchScreen() }
      //  composable(Destination.signup.route) { RegistrationScreen(navController=navController, onNavigateBack = {}) }
        composable(Destination.fooddescription.route) { //DisplayItemDiscreption(navController=navController, menuItemid = 8)
                navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("itemid")?.toInt()
            DisplayItemDiscreption(navController=navController, menuItemid = id!!,sharedViewModel=sharedViewModel) }
        composable(
            route = "categories/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("id") ?: ""
            CategoriesScreen(id = restaurantId,navController)
        }

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
            RegistrationScreen(navController=navController,
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


package com.example.talabi

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
               // MainScreeen()
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


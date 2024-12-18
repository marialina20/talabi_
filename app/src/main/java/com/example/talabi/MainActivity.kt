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
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.privacysandbox.tools.core.model.Type
import com.example.myapplication.ui.theme.DisplayLieuPage
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
            // DisplayItemDiscreption(menuItemid = 7, navController =navController)
            //DisplayPayementInfo(userid = 1, orderid = 1)
             //  DisplayCardItems()
           // DisplayRestaurantMenu(navController = )
                MyBottomNavigation()

                // DialogScreen()
              //  RatingDialogScreen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreen(navController: NavHostController,modifier:Modifier =Modifier) {

    NavHost(navController=navController, startDestination= Destination.RestaurantMenu.route

    ) {

        composable(Destination.RestaurantMenu.route) { DisplayRestaurantMenu(navController) }
        composable(Destination.FoodDescription.route) {
            DisplayItemDiscreption(menuItemid = 7, navController =navController )
        }
         composable(Destination.Card.route) { DisplayCardItems(navController) }
         composable(Destination.Notification.route) { NotifListt() }
         composable(Destination.PayementandAddress.route) { DisplayPayementInfo(userid = 1, orderid = 1,navController) }
         composable(Destination.LieuPage.route) { DisplayLieuPage(navController) }

//
//        composable(Destination.Ecron4.route) { Ecron4(navController) }
//        composable(Destination.Ecron4.route) { Ecron4(navController) }
//        composable(Destination.Ecron5.route) { Ecron5() }
//        composable(Destination.Ecron6.route) { Ecron6(navController) }
//        composable(PdfPageGotoLinkContent.Destination.Ecron7.route) { Ecron7() }

    }
    }

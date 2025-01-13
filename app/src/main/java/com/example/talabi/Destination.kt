package com.example.talabi


sealed class Destination(val route:String) {
    object RestaurantMenu:Destination("RestaurantMenu"){
        const val routed = "restaurant_menu/{restaurantId}"
    }
//    object FoodDescription:Destination("FoodDescription") {
//        /*fun getDestination(id:Int):String {
//            return this.route.replace("{idUser}",id.toString())
//        }*/
//    }
    object Card:Destination("Card")
    object Restaumenu:Destination("restaumenu")
    object Notification:Destination("Notification")
   // object Profil:Destination("Profil")
    object PayementandAddress:Destination("PayementandAddress")
    object home:Destination("home")
    object search:Destination("search")
    object restaurant_details:Destination("restaurant_details")
    object more:Destination("more")
    object LieuPage:Destination("LieuPage")
    object categories:Destination("categories")
    object login:Destination("login")
    object signup:Destination("signup")
    object language:Destination("language")
    object settings:Destination("settings")
    object profile:Destination("profile")
    object fooddescription : Destination("fooddescription/{itemid}") {
        fun getDestination(id: Int): String {
            return this.route.replace("{itemid}", id.toString())
        }
    }
    object editprofile : Destination("editprofile/{userid}") {
        fun getDestination(id: Int): String {
            return this.route.replace("{userid}", id.toString())
        }
    }


}
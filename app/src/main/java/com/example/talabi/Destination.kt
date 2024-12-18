package com.example.talabi


sealed class Destination(val route:String) {
    object RestaurantMenu:Destination("RestaurantMenu")
    object FoodDescription:Destination("FoodDescription") {
        /*fun getDestination(id:Int):String {
            return this.route.replace("{idUser}",id.toString())
        }*/
    }
    object Card:Destination("Card")
    object Notification:Destination("Notification")
    object Profil:Destination("Profil")
    object PayementandAddress:Destination("PayementandAddress")
    object LieuPage:Destination("LieuPage")
}
package com.example.talabi.Composants

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white
import coil.compose.AsyncImage
import com.example.talabi.Location
import com.example.talabi.Menu
import com.example.talabi.R
import com.example.talabi.Restaurant
import com.example.talabi.SocialMediaLinks
import com.example.talabi.api.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun MenuItemImage(image: String,imagesize:Int= 100){
   // val menuItem= getMenuItemById(id = menuItemid)
    Box (
        //modifier = Modifier.background(AppTheme.colors.onBackground)
    ){
        AsyncImage(
            model = image, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(imagesize.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp))))
        Row (
            modifier = Modifier.offset(x=75.dp,y=20.dp),
            //horizontalArrangement = Arrangement.End

        ){
            FavoriteCirculedOutlineButton(
                imageVector = Icons.Filled.Favorite,
                sizeButton = 30,
                sizeIcon = 20,
                containerColor = Color(0xfFf1ebe9),
                borderStroke = 0.001f,
                borderColor = white,
            )

        }
    }
}
@Composable
fun CardItemImage(image:String ){
   // val menuItem= getMenuItemById(id = menuItemid)
    Box (
        //modifier = Modifier.background(AppTheme.colors.onBackground)
    ){
        AsyncImage(
            model = image,
             contentDescription = null,
            contentScale  = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(90.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp))))

    }
}
@Composable
fun DisplayRestaurantImage(
    restau_id:Int
){
    val restau = getRestaurantById(restau_id)
    Card (
        modifier= Modifier
            .padding(horizontal = 3.dp, vertical = 1.dp)

    ){
        Box (
            modifier = Modifier.background(white)

        ) {
            Image(painter = painterResource(id = restau!!.image), contentDescription =null,
                modifier = Modifier.fillMaxWidth())
            Row (
                modifier = Modifier.padding(30.dp),
                horizontalArrangement = Arrangement.spacedBy(90.dp)
            ){
                Column {
                    Text(text = restau.name, style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold), color = white)
                    Row {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "",
                            tint = white
                        )
                        Text(text = restau.location.toString(), style = TextStyle(fontSize = 16.sp), color = white)
                    }
                }
                Column (
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    CirculedOutlineButton(sizeButton = 37, sizeIcon =23 , imageVector = Icons.Filled.Search, containerColor = Color.Unspecified, borderStroke = 1f, onClick = {}, borderColor = white)
                    CirculedOutlineButton(sizeButton = 37, sizeIcon =23 , imageVector = Icons.Filled.Notifications, containerColor = Color.Unspecified, borderStroke = 1f, onClick = {}, borderColor = white)
                  //  CirculedOutlineButton(sizeButton = 37, sizeIcon =23 , imageVector = Icons.Filled.Star, containerColor = Color.Unspecified, borderStroke = 1f,onClick = { RatingDialog()}, borderColor = white)
                   // RatingDialog()
                    RestaurantRatingDialog(6, userId = 1,sizeButton = 37,
                        containerColor = Color.Unspecified,
                        borderStroke = 1f,
                        borderColor = Color.Black) }
            }


        }

    }
}

@Composable
fun DisplayRestaurantImage3(
    restaurantId:String
){
    val coroutineScope = rememberCoroutineScope()
    var menuList2 by remember { mutableStateOf(Restaurant(
        id = 1,
        name = "Pizza Palace",
        logo = "photo1",
        address = "123 Main Street",
        location = Location(x = 40.7128, y = -74.006),
        cuisine_type = "Italian",
        average_rating = 4.5,
        number_of_reviews = 150,
        contact_phone = "123-456-7890",
        contact_email = "info@pizzapalace.com",
        social_media_links = SocialMediaLinks(
            facebook = "facebook.com/pizzapalace",
            instagram = "instagram.com/pizzapalace"
        )
    )
    ) }
    LaunchedEffect(restaurantId) {
        coroutineScope.launch {
            try {
                // Fetch menu items by restaurant ID
                val response = RetrofitInstance.api.getRestaurantById(restaurantId)
                if (response.isSuccessful) {
                    menuList2 = response.body()!!
                    Log.e("lisssttttttttttttttttt", "Error: ${menuList2}")
                } else {
                    Log.e("Restauranssssssssssssssssssss", "Error: ${response.code()}")

                }
            } catch (e: Exception) {
                Log.e("RestaurantMenuScreen", "Error fetching menu: ${e.localizedMessage}")
            }
        }
    }


    Card (
        modifier= Modifier
            .padding(horizontal = 3.dp, vertical = 1.dp)

    ){
        Box (
            modifier = Modifier.background(white)

        ) {
            AsyncImage(model = menuList2.logo,
             //   model = R.drawable.rimg10,
            contentDescription =null,
                modifier = Modifier.fillMaxWidth())
            Row (
                modifier = Modifier.padding(30.dp),
                horizontalArrangement = Arrangement.spacedBy(90.dp)
            ){
                Column {
                    Text(text = menuList2.name, style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold), color = white)
                    Row {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "",
                            tint = white
                        )
                        Text(text = menuList2.address, style = TextStyle(fontSize = 16.sp), color = white)
                    }
                }
                Column (
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    CirculedOutlineButton(sizeButton = 37, sizeIcon =23 , imageVector = Icons.Filled.Search, containerColor = Color.Unspecified, borderStroke = 1f, onClick = {}, borderColor = white)
                    CirculedOutlineButton(sizeButton = 37, sizeIcon =23 , imageVector = Icons.Filled.Notifications, containerColor = Color.Unspecified, borderStroke = 1f, onClick = {}, borderColor = white)
                    //  CirculedOutlineButton(sizeButton = 37, sizeIcon =23 , imageVector = Icons.Filled.Star, containerColor = Color.Unspecified, borderStroke = 1f,onClick = { RatingDialog()}, borderColor = white)
                    // RatingDialog()
                    RestaurantRatingDialog(restaurantId.toInt(), userId = 1,sizeButton = 37 , containerColor = Color.Unspecified, borderStroke = 1f,borderColor = white)
                }
            }


        }

    }
}

@Composable
fun DisplayRestaurantImage2(
    category:String
){
    val imageRes = when (category) {
        "Italian" -> R.drawable.burger1
        "Chinese" -> R.drawable.chinese
        "Pizza" -> R.drawable.pizza1
        else -> R.drawable.salade // Fallback image
    }
    Card (
        modifier= Modifier
            .padding(horizontal = 1.dp, vertical = 1.dp)

    ){
        Box (
            modifier = Modifier.background(white)

        ) {
            Image(painter = painterResource(id = imageRes), contentDescription =null,
                modifier = Modifier.fillMaxWidth())
            Row (
                modifier = Modifier.padding(30.dp),
                horizontalArrangement = Arrangement.spacedBy(90.dp)
            ){
                Column {
                    Text(text = category, style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold), color = white)

                }

            }


        }

    }
}
//@Composable
//fun DescriptionItemImage(menuItemid: Int,imagesize:Int= 100){
//   // val menuItem= getMenuItemById(id = menuItemid)
//    Box (
//        modifier = Modifier.background(orange)
//    ){
//        Card(
//            modifier = Modifier
//                .background(Color.White)
//                .shadow(2.dp, shape = RoundedCornerShape(corner = CornerSize(16.dp)),),
//            shape = RoundedCornerShape(16.dp),
//            colors = CardColors(
//                containerColor = white,
//                contentColor = Color.Transparent,
//                disabledContentColor = Color.Unspecified,
//                disabledContainerColor = Color.Unspecified
//            )
//
//        ) {
//            AsyncImage(model = menuItemid!!.image, contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    //.padding(5.dp)
//                    .size(height = 200.dp, width = 1000.dp)
//                    .clip(RoundedCornerShape(corner = CornerSize(16.dp))))
//
//        }
//        Row (
//            modifier = Modifier.offset(x=265.dp,y=5.dp),
//            //horizontalArrangement = Arrangement.End
//
//        ){
//            FavoriteCirculedOutlineButton(imageVector = Icons.Filled.Favorite, sizeButton = 50, sizeIcon = 30, containerColor = Color(0xfFf1ebe9), borderStroke = 0.001f, borderColor = white,
//            )
//
//        }
//
//    }
//}
@Composable
fun RestaurantMenuItemImage(image: String,imagesize:Int= 130){
    // val menuItem= getMenuItemById(id = menuItemid)
    Box (
        //modifier = Modifier.background(AppTheme.colors.onBackground)
    ){
        AsyncImage(
            model = image, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(imagesize.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp))))
//        Row (
//            modifier = Modifier.offset(x=75.dp,y=20.dp),
//            //horizontalArrangement = Arrangement.End
//
//        ){
//            FavoriteCirculedOutlineButton(
//                imageVector = Icons.Filled.Favorite,
//                sizeButton = 30,
//                sizeIcon = 20,
//                containerColor = Color(0xfFf1ebe9),
//                borderStroke = 0.001f,
//                borderColor = white,
//            )
//
//        }
    }
}
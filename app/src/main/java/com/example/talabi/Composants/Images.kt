package com.example.talabi.Composants

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
                    CircledRatingDialog(sizeButton = 37, containerColor = Color.Unspecified, borderStroke = 1f, onClick = {}, borderColor = white)
                }
            }


        }

    }
}
@Composable
fun DescriptionItemImage(menuItemid: Int,imagesize:Int= 100){
    val menuItem= getMenuItemById(id = menuItemid)
    Box (
        modifier = Modifier.background(orange)
    ){
        Card(
            modifier = Modifier
                .background(Color.White)
                .shadow(2.dp, shape = RoundedCornerShape(corner = CornerSize(16.dp)),),
            shape = RoundedCornerShape(16.dp),
            colors = CardColors(
                containerColor = white,
                contentColor = Color.Transparent,
                disabledContentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified
            )

        ) {
            Image(painter = painterResource(id = menuItem!!.image), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(5.dp)
                    .size(height = 200.dp, width = 1000.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp))))

        }
        Row (
            modifier = Modifier.offset(x=265.dp,y=5.dp),
            //horizontalArrangement = Arrangement.End

        ){
            FavoriteCirculedOutlineButton(imageVector = Icons.Filled.Favorite, sizeButton = 50, sizeIcon = 30, containerColor = Color(0xfFf1ebe9), borderStroke = 0.001f, borderColor = white,
            )

        }

    }
}
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
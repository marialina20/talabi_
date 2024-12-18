package com.example.talabi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talabi.Composants.CircularAddButton
import com.example.talabi.Composants.DescriptionItemImage
import com.example.talabi.Composants.ExtendedButton
import com.example.talabi.Composants.RatingDialog
import com.example.talabi.Composants.TopBar
import com.example.talabi.Composants.getMenuItemById
import com.example.talabi.ui.theme.AppTheme
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.gray2
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white



@Composable
fun DisplayItemDiscreption(menuItemid:Int,navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val menuItem = getMenuItemById(id = menuItemid)
    Box (
        modifier = Modifier.fillMaxSize() ,
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TopBar(
                content = "Discreption",
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onClick = {navController.navigate(Destination.RestaurantMenu.route) }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                DescriptionItemImage(menuItemid = menuItemid)
            }
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = menuItem!!.name,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            Column (
                modifier = Modifier
                    .padding(5.dp),
             //   verticalArrangement = Arrangement.spacedBy(.dp)
            ){
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "\$ ${menuItem.price}",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = orange)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    // modifier = Modifier.,
                )
                {

                    RatingDialog()
                    Text(
                        text = " ${menuItem.averageRating}",
                        style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 16.sp)
                    )
                }
            }


            HorizontalDivider()
            Text(text ="Description:", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
           Text(text = menuItem.description, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal))

        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = white.copy(0.9f),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            shape = RoundedCornerShape(16.dp)

        )
        {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .fillMaxWidth()
            ) {


                var numberofItem = 0
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(3.dp),
                ) {
                    CircularAddButton(
                        onClick = { numberofItem++ },
                        content = "+",
                        contentColor = gray,
                        buttonColor = gray2,
                        size = 40,
                        contentsize = 25
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "$numberofItem")
                    Spacer(modifier = Modifier.size(4.dp))
                    CircularAddButton(
                        onClick = { numberofItem-- },
                        content = "-",
                        contentColor = gray,
                        buttonColor = gray2,
                        size = 40,
                        contentsize = 30
                    )
                }
                ExtendedButton(content = "Add to my Card", imageVector = Icons.Filled.ShoppingCart,onClick = {})
            }

        }

    }
}
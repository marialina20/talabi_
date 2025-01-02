package com.example.talabi.Composants

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talabi.Destination
import com.example.talabi.Menu
import com.example.talabi.Restaurant
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.data.menuItems
import com.example.talabi.ui.theme.AppTheme
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.gray2
import com.example.talabi.ui.theme.orange
import kotlinx.coroutines.launch

@Composable
fun DisplayoneCarditem(menuItemId: Int) {
    var numberofItem = 0
    val menuItem = getMenuItemById(menuItemId)
    Card(

        modifier = Modifier
            .shadow(shape = RoundedCornerShape(16.dp), elevation = 10.02.dp)
            .padding(bottom = 5.dp)
            .fillMaxWidth()
            .clickable(onClick = {}),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),

        ) {

        Row(
            modifier = Modifier
                .background(color = AppTheme.colors.background)
                .height(IntrinsicSize.Min)
        ) {
            CardItemImage(menuItemId)

            Column(
                modifier = Modifier
                    .padding(1.dp)
                    .align(Alignment.CenterVertically)
                    .width(IntrinsicSize.Max)

            ) {
                Text(
                    text = " ${menuItem!!.name}",
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(3.dp),
                ) {
                    //Spacer(modifier = Modifier.size(30.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(3.dp),
                    ) {
                        Text(
                            text = "$",
                            style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = orange
                            )
                        )
                        //Text(text = " ${menuItem.averageRating}",style= TextStyle(fontStyle = FontStyle.Italic, fontSize = 14.sp))
                        Text(
                            text = " ${menuItem.price}",
                            style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = orange
                            )
                        )
                    }
                }
//
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
                   // Text(text = DialogScreen().inputText)
                }


            }
            Column(

                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxSize()
                    .width(IntrinsicSize.Max)
                    .padding(horizontal = 2.dp),
            ) {
                //Spacer(modifier = Modifier.size(6.dp))


                DialogScreen()
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    containerColor = gray2,
                    contentColor = gray,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Add")
                }

            }


        }

    }


}


@Composable
fun DisplayoneMenuItem(menuList: List<Menu>,menuItemId: Int,navController: NavHostController) {



    val menuItem = menuList.find { it.restaurant_id == menuItemId }
    Card(
        modifier = Modifier
            .shadow(shape = RoundedCornerShape(16.dp), elevation = 10.02.dp)
            .padding(bottom = 5.dp)
            .fillMaxWidth()
            .clickable(onClick = {navController.navigate(Destination.FoodDescription.route) }),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),

        ) {

        Row(
            modifier = Modifier.background(color = AppTheme.colors.background)
        ) {
            MenuItemImage(menuItemId)
            Column(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)

            ) {
                Text(
                    text = " ${menuItem!!.name}",
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(3.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(3.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star icon",
                            tint = AppTheme.colors.secondarySurface, // Change the icon color
                            modifier = Modifier.size(24.dp) // Change the icon size
                        )
                        Text(
                            text = " ${menuItem.average_rating}",
                            style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 14.sp)
                        )
                        //Text(text = " ${menuItem.price}",style= TextStyle(fontStyle = FontStyle.Italic, fontSize = 14.sp))
                    }
                    Spacer(modifier = Modifier.size(30.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(3.dp),
                    ) {
                        Text(
                            text = "$",
                            style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = orange
                            )
                        )
                        //Text(text = " ${menuItem.averageRating}",style= TextStyle(fontStyle = FontStyle.Italic, fontSize = 14.sp))
                        Text(
                            text = " ${menuItem.price}",
                            style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = orange
                            )
                        )
                    }
                }
//
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                ) {
                    CircularAddButton(
                        onClick = { /*TODO*/ },
                        content = "+",
                        contentColor = Color.White,
                        buttonColor = AppTheme.colors.actionSurface,
                        size = 50
                    )
                }

            }

        }
    }
}

package com.example.talabi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talabi.Composants.CircularAddButton
import com.example.talabi.Composants.CirculedOutlineButton
import com.example.talabi.Composants.DisplayRestaurantImage
import com.example.talabi.Composants.DisplayoneMenuItem
import com.example.talabi.Composants.MenuItemImage
import com.example.talabi.Composants.SquareButton
import com.example.talabi.data.MenuItem
import com.example.talabi.data.Restaurant
import com.example.talabi.data.menuItems
import com.example.talabi.data.restaurants
import com.example.talabi.ui.theme.AppTheme
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white


@Composable
fun DisplayRestaurantMenu(navController: NavHostController) {
    val menu= remember { menuItems}
    Column (modifier = Modifier
        .padding(vertical = 24.dp, horizontal = 8.dp)){
        DisplayRestaurantImage(restau_id = 1)
        Spacer(modifier = Modifier.height(12.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
        }
        //Text(text = "Check our famous Restaurants :",style= TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues())

            ) {
            items(

                items = menu,
                itemContent = {
                    DisplayoneMenuItem(menuItemId = it.id,navController)
                }

            )

        }
    }
}




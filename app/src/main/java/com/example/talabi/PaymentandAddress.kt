package com.example.talabi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.talabi.Composants.ExtendedButton
import com.example.talabi.Composants.TopBar
//import com.example.talabi.Composants.getOrderById
import com.example.talabi.Composants.getUserById
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.gray2
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayPayementInfo(userid: Int,orderid: Int,navController: NavHostController)
{
    var showOrderValid = remember { mutableStateOf(false) }
    var blur=0
    var user= getUserById(id = userid)
   // var order = getOrderById(id = orderid)
    var note = remember {
        mutableStateOf(TextFieldValue())
    }
    Box (
        //modifier = Modifier.blur(blur = if (showOrderSuccess.value) 12 else 0)
    ){


        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(10.dp)
                .blur(if (showOrderValid.value) 8.dp else 0.dp)

        ) {
            TopBar(
                content = "Payement",
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onClick = { navController.navigate(Destination.Card.route) })
            Text(
                text = "You deserve better meal",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, color = gray)
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))
            Text(
                text = "Details Transaction :",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            DisplayoneInfo(label = "Cherry Healthy", infoText = "\$ 180.000")
            DisplayoneInfo(label = "Driver", infoText = "\$ 50.000")
            DisplayoneInfo(label = "Tax 10%", infoText = "\$ 80.000")
            DisplayoneInfo(label = "Total price", infoText = "\$ 100.000")
            Spacer(modifier = androidx.compose.ui.Modifier.height(30.dp))
            HorizontalDivider(modifier = Modifier.padding(10.dp))
            Text(
                text = "Deliver to ",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            DisplayoneInfo(label = "Name", infoText = user!!.name)
            DisplayoneInfo(label = "Phone No.", infoText = user.phone)
            //DisplayoneInfo(label = "Address", infoText = user.address)
            DisplayAddresseInfo(navController, label = "Address")

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 150.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center,
                //verticalAlignment = Alignment.Center
            ) {
                var isVisible = remember { mutableStateOf(true) }
                AnimatedVisibility(
                    visible = isVisible.value,
                    exit = fadeOut() + scaleOut(targetScale = 0.8f)
                ) {
                    if( !showOrderValid.value) {
                        ExtendedButton(
                            content = "Checkout",
                            imageVector = Icons.Filled.ShoppingCart,
                            onClick = {
                                showOrderValid.value = true
                            })
                    }
                    else {
                        Text(text = "", style = TextStyle(color = Color.Unspecified))
                    }
                    if (showOrderValid.value) {
                        OrderValideScreen {
                            showOrderValid.value = false // Revenir à l'écran principal
                        }
                    }
                }
                AnimatedVisibility(
                    visible = showOrderValid.value,
                    enter = fadeIn(animationSpec = tween(500)) + scaleIn(
                        initialScale = 0.8f,
                        animationSpec = tween(500)
                    ),
                    exit = fadeOut(animationSpec = tween(500)) + scaleOut(
                        targetScale = 0.8f,
                        animationSpec = tween(500)
                    )
                ) {
                    OrderValideScreen {
                        showOrderValid.value = false
                    }
                }

        }
    }

}
@Composable
fun DisplayoneInfo(
    label:String,
    infoText: String
){
    Row (
       modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(text = "$label :", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal))
        Text(text = infoText, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
    }
}
@Composable
fun DisplayAddresseInfo(navController: NavHostController,
    label:String,
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(text = "$label :", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal))
        //Text(text = infoText, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
        FloatingActionButton(
            onClick = {  navController.navigate(Destination.LieuPage.route)},
            containerColor = gray2,
            contentColor = gray,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = "Add")
        }
    }
}

@Composable
fun OrderValideScreen(onDismiss: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = 0.1f)), // Fond semi-transparent
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success",
                tint = Color(0xFFFFA500),
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = "Order Valide",
                style = TextStyle(
                    fontSize = 32.sp,
                    color = Color(0xFFFFA500),
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onDismiss,
                colors = ButtonColors(
                    containerColor = orange,
                    contentColor = white,
                    disabledContentColor = white,
                    disabledContainerColor = white
                ),) {
                Text("Close")
            }
        }
    }
}
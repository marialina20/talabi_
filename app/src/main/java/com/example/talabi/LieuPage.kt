package com.example.myapplication.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talabi.Composants.TopBar
import com.example.talabi.Destination
import com.example.talabi.R
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.gray2
import com.example.talabi.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun DisplayLieuPage(navController: NavHostController) {
    //paddingValues: PaddingValues
    val addressText = remember { mutableStateOf("") }
    val specialInstructions = remember { mutableStateOf("") }
    Box (
        modifier = Modifier.fillMaxSize()
    ){


        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
            ) {
                //  Spacer(modifier = Modifier.height(100.dp))
                Image(
                    painter = painterResource(R.drawable.img_5),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomEnd = 24.dp, bottomStart = 24.dp)),
                       // .shadow(elevation = 10.dp, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomEnd = 24.dp, bottomStart = 24.dp)),
                    contentScale = ContentScale.Crop

                )
                TopBar(
                    content = "Titre ou Description",
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    onClick = {navController.navigate(Destination.PayementandAddress.route)},
                    padding = 20,
                    contentColor = white
                )


            }



            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    //.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                   // .shadow(elevation = 10.dp, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color.White) // Fond blanc
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                // .align(Alignment.BottomCenter)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
//                Image(
//                    painter = painterResource(R.drawable.img_6),
//                    contentDescription = null,
//                    modifier = Modifier.size(32.dp),
//                    contentScale = ContentScale.Crop
//                )
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "",
                        tint = gray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Adresse de livraison",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Entrez votre adresse",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                TextField(
                    value = addressText.value,
                    onValueChange = { addressText.value = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = gray2,
                        unfocusedIndicatorColor = gray,
                        cursorColor = gray,
                        focusedLabelColor = gray// Placeholder color
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            "ex: maison Numero 10 , City KDA KDA .",
                        )
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Ajouter des instructions spéciales:",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                TextField(
                    value = specialInstructions.value,
                    onValueChange = { specialInstructions.value = it },
                    colors = TextFieldDefaults.run {
                        textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = gray2,
                            unfocusedIndicatorColor = gray,
                            cursorColor = gray,
                            focusedLabelColor = gray// Placeholder color
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            "ex: Kayen doura capable ttlaflak fiha " +
                                    "brozer , ki towsel 3nd doura t3 sbitar 3awed weli ldarkom , coghdialemen",
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))





                Button(
                    onClick = {
                        navController.navigate(Destination.PayementandAddress.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFE8C00) // Couleur personnalisée
                    )

                ) {
                    Text(text = "Confirmer ma commande", fontSize = 16.sp)
                }
            }
        }
    }
}

package com.example.restaurantlist.ui.theme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.layout.ContentScale
import com.example.talabi.Composants.TopBar
import com.example.talabi.R
import com.example.talabi.ui.theme.white

@Composable
fun StaticMapScreen() {
    Box(modifier = Modifier

        .fillMaxSize()

        .background(color = white)) {

        Image(
            painter = painterResource(id = R.drawable.img_13),
            contentDescription = "Carte statique",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            Row (verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()){
              // Spacer(modifier = Modifier.width(4.dp))
//                Image(
//                    painter = painterResource(R.drawable.img_18),
//                    contentDescription = null,
//                    modifier = Modifier.size(40.dp)
//                )
//                Spacer(modifier = Modifier.width(45.dp))
//                Text(text = "Specify your address",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold
//
//                  )
                TopBar(content = "Specify your address", imageVector =  Icons.AutoMirrored.Filled.KeyboardArrowLeft,onClick={})


            }



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                        shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
                //elevation = 6.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),


                ) {
                    Button(
                        onClick = { /* Action de confirmation */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xfFffffff))
                    ) {
                        Row {
                            Text(
                                text = "CITY KDA",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Blue
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ID 213752",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Blue
                            )
                        }
                    }
                    // Adresse


                    Spacer(modifier = Modifier.height(16.dp))


                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Your Delivery Time", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Estimated 8:30 - 9:15 PM",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = painterResource(R.drawable.img_19),
                        contentDescription = null,
                        //modifier = Modifier.size(140.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))


                    Button(
                        onClick = { /* Action de confirmation */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xfFfE8C00))
                    ) {
                        Text(
                            text = "Confirm address",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            }
        }
    }



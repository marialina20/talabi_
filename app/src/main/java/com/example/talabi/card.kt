import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.talabi.Composants.DisplayoneCarditem
import com.example.talabi.Composants.ExtendedButton
import com.example.talabi.Composants.TopBar
import com.example.talabi.Destination
import com.example.talabi.data.menuItems
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white

@Composable
fun DisplayCardItems(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    var prixtotal :Double=0.0
    val menu = remember { menuItems }
    Column {

       TopBar(content = "My Card", imageVector =  Icons.AutoMirrored.Filled.KeyboardArrowLeft,onClick = {})
        Box(
            modifier = Modifier.fillMaxSize() ,

        )
        {
            Column(
                modifier = Modifier
                    .padding(top = 4.dp).background(Color.Transparent)
            ) {
               // Spacer(modifier = Modifier.height(12.dp))
                //Text(text = "Check our famous Restaurants :",style= TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                    modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()).padding(bottom = 200.dp)

                    ) {
                    items(

                        items = menu,
                        itemContent = {
                            DisplayoneCarditem(menuItemId = it.id)
                            prixtotal= prixtotal+it.price
                        }

                    )

                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = white.copy(0.9f),
                    contentColor = white.copy(0.9f),

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    //.padding(WindowInsets.navigationBars.asPaddingValues())
                    .padding(bottom = 90.dp)
                        ,
                shape = RoundedCornerShape(16.dp)

            )
            {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(vertical = 43.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {


                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(3.dp)
                                .background(Color.Transparent),
                        ) {
                            Text(
                                text = "Delivery fees : ",
                                style = TextStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = (screenHeight / 40).sp,
                                    color = orange
                                )
                            )

                            Text(
                                text = "500.00 $",
                                style = TextStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = gray
                                )
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(3.dp)
                                .background(Color.Transparent),
                        ) {
                            Text(
                                text = "Total price : ",
                                style = TextStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = orange
                                )
                            )
                            Text(
                               // text = " ${prixtotal} $",
                                text = " 100.000 $",
                                style = TextStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = gray
                                )
                            )
                        }

                    }

                    ExtendedButton(content = "Checkout", imageVector = Icons.Filled.ShoppingCart,onClick = { navController.navigate(
                        Destination.PayementandAddress.route)})

                }
            }


        }
    }

}


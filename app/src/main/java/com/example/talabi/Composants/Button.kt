package com.example.talabi.Composants

import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.talabi.RatingFoodRequest
import com.example.talabi.RatingRequest
import com.example.talabi.api.RetrofitInstance
import com.example.talabi.ui.theme.AppTheme
import com.example.talabi.ui.theme.blue
import com.example.talabi.ui.theme.gray
import com.example.talabi.ui.theme.gray2
import com.example.talabi.ui.theme.orange
import com.example.talabi.ui.theme.white
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CircularAddButton(
    onClick:  () -> Unit,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = white,
    content:String,
    size: Int = 56,
    contentsize : Int =30
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = contentColor,
        ),
        modifier = Modifier.size(size.dp).clickable {  }
    ) {
        Text(
            text = content,
            fontSize = contentsize.sp,
            textAlign = TextAlign.Center,
            //style = TextStyle(color = Color.White),
        )

    }
}

@Composable
fun CirculedOutlineButton(
    onClick: () -> Unit,
    sizeButton: Int,
    sizeIcon: Int,
    imageVector: ImageVector,
    containerColor: Color,
    contentColor: Color = white,
    borderStroke: Float,
    borderColor: Color
) {
     var contentcolor2= remember {
         mutableStateOf( white)
     }
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.size(sizeButton.dp),
        shape = CircleShape,
        border = BorderStroke(borderStroke.dp, color = borderColor),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContentColor = gray,
            disabledContainerColor = gray,
        )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.size(sizeIcon.dp)
        )
    }
}
@Composable
fun SquareButton(image:Int,content:String,buttoncolor:Color,sizeButton:Int,imagesize:Int, isSelected:Boolean,onClick: () -> Unit){


    OutlinedButton(onClick = onClick,
        modifier = Modifier
            .size(sizeButton.dp)
            .clickable(onClick = {}),

        border = BorderStroke(0.001.dp, color = white),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            containerColor = if (isSelected) orange else white,
            contentColor = white,
            disabledContentColor = white,
            disabledContainerColor = white,
        )
    ) {
        Column (
            verticalArrangement = Arrangement.Center
        ){
            Image(painter = painterResource(id = image), contentDescription =null,
                modifier=Modifier.size(imagesize.dp))
            Text(text = content, style = TextStyle(fontSize = 14.sp,color=AppTheme.colors.onSecondarySurface))
        }

    }
}
@Composable
fun ExtendedButton(content:String,imageVector: ImageVector,onClick:() -> Unit)
{
    ExtendedFloatingActionButton(
        onClick = onClick,
        icon = {
            Icon(
                imageVector = imageVector,
                "Extended floating action button.",
                modifier = Modifier.size(18.dp),
                tint = Color.White
            )
        },
        text = {
            Text(
                text =content,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        containerColor = orange,
        contentColor = orange,
        shape = RoundedCornerShape(16.dp),
        //modifier = Modifier.size(100.dp)
        //contentPadding = PaddingValues(0.dp),

    )
}
@Composable
fun FavoriteCirculedOutlineButton(
    //onClick: () -> Unit,
    sizeButton: Int,
    sizeIcon: Int,
    imageVector: ImageVector,
    containerColor: Color,
    //contentColor: Color = white,
    borderStroke: Float,
    borderColor: Color
) {
    val contentColor= remember {
        mutableStateOf( white)
    }
    var isFavorite = remember {
        mutableStateOf(false)
    }
    OutlinedButton(
        onClick = {
            isFavorite.value = !isFavorite.value
            if(isFavorite.value)
            {
                contentColor.value=Color.Red
               // contentColor=contentcolor2.value
            }
            else
            {
                contentColor.value=white
            }
        },
        modifier = Modifier.size(sizeButton.dp),
        shape = CircleShape,
        border = BorderStroke(borderStroke.dp, color = borderColor),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor.value,
            disabledContentColor = gray,
            disabledContainerColor = gray,
        )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.size(sizeIcon.dp)
        )
    }
}

//given by chatgpt--------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableTextDialog(
    initialText :String ="no cucumber...",
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    if (showDialog) {
        var text = remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Additional notes")
            },
            text = {
                Column {
                    //Text(text = "Please enter your input below:")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        colors = TextFieldDefaults.textFieldColors(

                            containerColor = Color.Transparent,
                            focusedIndicatorColor = gray2,
                            unfocusedIndicatorColor = gray,
                            cursorColor = gray  ,
                            focusedLabelColor = gray
                        ),
                        value = text.value,
                        onValueChange = { text.value = it },
                        placeholder = { Text("no cucumber...") }
                    )
                }
            },
            confirmButton = {
                Button(
                    colors = ButtonColors(
                        containerColor = orange,
                        contentColor = white,
                        disabledContentColor = white,
                        disabledContainerColor = white
                    ),
                    onClick = {
                        onConfirm(text.value)
                        onDismiss()
                    }

                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonColors(
                        containerColor = orange,
                        contentColor = white,
                        disabledContentColor = white,
                        disabledContainerColor = white
                    ),
                    onClick = onDismiss
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun DialogScreen() {
    var showDialog = remember { mutableStateOf(false) }
    var additionalNote = remember { mutableStateOf("") }

        FloatingActionButton(
            onClick = { showDialog.value = true } ,
            containerColor = gray2,
            contentColor = gray,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Add")
        }

//        if (additionalNote.value.isNotEmpty()) {
//           Text(text = "Input: ${inputText.value}", modifier = Modifier.padding(top = 16.dp))
//        }

        EditableTextDialog(
            initialText = "additionalNote",
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false },
            onConfirm = { text ->

                additionalNote.value = text
                showDialog.value = false
            }
        )

}
@Composable
fun RatingDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    if (showDialog) {
        var rating = remember { mutableStateOf(0) } // Tracks selected star rating

        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Rate this dish")
            },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "How would you rate your experience?")
                    Spacer(modifier = Modifier.height(16.dp))

                    // Row of stars for rating
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        for (i in 1..5) {
                            Icon(
                                imageVector = if (i <= rating.value) Icons.Default.Star else Icons.Default.Star,
                                contentDescription = "Star $i",
                                tint = if (i <= rating.value) orange else Color.Gray,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable {
                                        rating.value = i // Update rating based on clicked star
                                    }
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    colors = ButtonColors(
                        containerColor = orange,
                        contentColor = white,
                        disabledContentColor = white,
                        disabledContainerColor = white
                    ),
                    onClick = {
                    onConfirm(rating.value) // Pass the selected rating to the parent
                    onDismiss() // Dismiss the dialog
                }) {
                    Text("Submit")
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonColors(
                        containerColor = orange,
                        contentColor = white,
                        disabledContentColor = white,
                        disabledContainerColor = white
                    ),
                    onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun RatingDialog() {
    var showDialog = remember { mutableStateOf(false) }
    var userRating = remember { mutableStateOf(0) }
    var userComment = remember{ mutableStateOf("") }
    val context = LocalContext.current


    IconButton(onClick = { showDialog.value = true },
            modifier = Modifier.padding(0.dp),
            content = { Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star icon",
                tint = AppTheme.colors.secondarySurface,
                modifier = Modifier.size(24.dp))}
            )

//        if (userRating.value > 0) {
//            Text(text = "Your Rating: ${userRating.value} Stars", modifier = Modifier.padding(top = 16.dp))
//        }
        RatingDialog(
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false },
            onConfirm = { rating ->
                userRating.value = rating // Save the user's rating
                showDialog.value = false
            }
        )

}
@Composable
fun StarWithRatingDialog(
    foodId: Int, // Pass the restaurant ID
    userId: Int // Pass the user ID
) {
    var showDialog = remember { mutableStateOf(false) }
    var userRating = remember { mutableStateOf(0) }
    var userComment = remember { mutableStateOf("") }
    val context = LocalContext.current

    // Star Icon (Button) to trigger the dialog
    Icon(
        imageVector = Icons.Filled.Star,
        contentDescription = "Rate this food",
        tint = AppTheme.colors.secondarySurface,
        modifier = Modifier
            .size(24.dp)
            .clickable {
                showDialog.value = true // Show the rating dialog on click
            }
    )

    // Display the Rating Dialog if `showDialog` is true
    if (showDialog.value) {
        Dialog(onDismissRequest = { showDialog.value = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color.White, // Set dialog background to white
                tonalElevation = 8.dp // Optional shadow elevation
            ) {
                // Dialog Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    // Title
                    Text("Rate this food:", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))

                    // Rating Stars
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (i in 1..5) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = if (i <= userRating.value) orange else Color.Gray,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { userRating.value = i }
                            )
                        }
                    }

                    // OutlinedTextField for the comment
                    OutlinedTextField(
                        value = userComment.value, // Access the current comment state
                        onValueChange = { userComment.value = it }, // Update state on text change
                        placeholder = { Text("Add a comment") }, // Placeholder text
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textStyle = TextStyle(fontSize = 16.sp),
                        label = { Text("Comment") } // Optional 'Comment' label
                    )

                    // Submit Button
                    Button(
                        colors = ButtonColors(
                            containerColor = orange,
                            contentColor = white,
                            disabledContentColor = white,
                            disabledContainerColor = white
                        ),
                        onClick = {
                            // Make API Call on Submit
                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    val apiService = RetrofitInstance.api
                                    val response = apiService.submitFoodRating(
                                        foodId = foodId,
                                        request = RatingFoodRequest(
                                            userId = userId,
                                            rating = userRating.value,
                                            comment = userComment.value
                                        )
                                    )
                                    Log.d(
                                        "CircledRatingDialog",
                                        "Success: ${response.average_rating}"
                                    )
                                } catch (e: Exception) {
                                    Log.e("CircledRatingDialog", "Error: ${e.message}")
                                }
                            }
                            showDialog.value = false // Dismiss dialog
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }
    }


@Composable
fun RestaurantRatingDialog(
    restaurantId: Int,
    userId: Int,
    sizeButton: Int,
    containerColor: Color,
    contentColor: Color = Color.White,
    borderStroke: Float,
    borderColor: Color
) {
    var showDialog = remember { mutableStateOf(false) }
    var userRating = remember { mutableStateOf(0) }
    var userComment = remember { mutableStateOf("") }
    val context = LocalContext.current

    // Button to trigger the dialog
    OutlinedButton(
        onClick = { showDialog.value = true },
        modifier = Modifier.size(sizeButton.dp),
        shape = CircleShape,
        border = BorderStroke(borderStroke.dp, color = borderColor),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Star icon",
            tint = AppTheme.colors.secondarySurface,
            modifier = Modifier.size(24.dp)
        )
    }

    // Dialog Implementation
    if (showDialog.value) {
        Dialog(onDismissRequest = { showDialog.value = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color.White, // Set dialog background to white
                tonalElevation = 8.dp // Optional shadow elevation
            ) {
                // Dialog Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    // Title
                    Text("Rate this restaurant:", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))

                    // Rating Stars
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (i in 1..5) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = if (i <= userRating.value) orange else Color.Gray,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { userRating.value = i }
                            )
                        }
                    }

                    // OutlinedTextField for the comment
                    OutlinedTextField(
                        value = userComment.value, // Access the current comment state
                        onValueChange = { userComment.value = it }, // Update state on text change
                        placeholder = { Text("Add a comment") }, // Placeholder text
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textStyle = TextStyle(fontSize = 16.sp),
                        label = { Text("Comment") } // Optional 'Comment' label
                    )

                    // Submit Button
                    Button(
                        colors = ButtonColors(
                            containerColor = orange,
                            contentColor = white,
                            disabledContentColor = white,
                            disabledContainerColor = white
                        ),
                        onClick = {
                            // Make API Call on Submit
                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    val apiService = RetrofitInstance.api
                                    val response = apiService.submitRating(
                                        restaurantId = restaurantId,
                                        request = RatingRequest(
                                            userId = userId,
                                            rating = userRating.value,
                                            comment = userComment.value
                                        )
                                    )
                                    Log.d(
                                        "CircledRatingDialog",
                                        "Success: ${response.average_rating}"
                                    )
                                } catch (e: Exception) {
                                    Log.e("CircledRatingDialog", "Error: ${e.message}")
                                }
                            }
                            showDialog.value = false // Dismiss dialog
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}






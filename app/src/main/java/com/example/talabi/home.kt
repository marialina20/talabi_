package com.example.ahlem
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.talabi.Destination
import com.example.talabi.R
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.talabi.Composants.CallButton
import com.example.talabi.Composants.DisplayRestaurantImage
import com.example.talabi.Composants.DisplayRestaurantImage2
import com.example.talabi.Composants.RestaurantMenuItemImage
import com.example.talabi.Menu
import com.example.talabi.Restaurant
import com.example.talabi.api.RetrofitInstance
import kotlinx.coroutines.launch



@Composable
fun MainScreeen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MyBottomNavigation(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("search") { SearchScreen() }
            composable(
                "categories/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getString("id") ?: ""
                CategoriesScreen(id = restaurantId, navController)
            }

            //composable("restaurant_details") { RestaurantDetailsScreen() }
            composable("more") { MoreRestaurantsScreen() }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F8))
    ) {
        // Header
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
Spacer(modifier=Modifier.height(20.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFFFE8C00))) {
                            append("Great ")
                        }
                        append("options")
                    },
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333A73)
                    )
                )
                Text(
                    text = "waiting for you !",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333A73)
                    )
                )
            }

        }


        // Search Bar
        SearchBar(navController)

        // Categories Section
        Text(
            text = "Categories",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF333A73))
        )
        CategoriesRow(navController)

        // Near Restaurants Section
        Text(
            text = "Near restaurants",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF333A73))
        )
        NearRestaurantsList(navController)
    }
}

@Composable
fun SearchBar(navController: NavController) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .width(250.dp) // Use only width to constrain the size
            .height(50.dp) // Height remains fixed
            .background(Color(0xFFE2E2E5), shape = RoundedCornerShape(30.dp))
            .clickable { navController.navigate("search") },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search Icon", tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Search", color = Color(0xFF666666))
        }
    }
}


@Composable
fun CategoriesRow(navController: NavController) {
    val categories = listOf(
//        Category(
//            id = java.util.UUID.randomUUID().toString(), // Assign random UUID
//            name = "Pizza",
//            imageRes = R.drawable.pizza1
//        ),
        Category(
            id = java.util.UUID.randomUUID().toString(), // Assign random UUID
            name = "Chinese",
            imageRes = R.drawable.chinese
        ),
        Category(
            id = java.util.UUID.randomUUID().toString(), // Assign random UUID
            name = "Italian",
            imageRes = R.drawable.burger1
        ),
        Category(
                id = java.util.UUID.randomUUID().toString(), // Assign random UUID
        name = "Indian",
        imageRes = R.drawable.img4
    ),
    Category(
        id = java.util.UUID.randomUUID().toString(), // Assign random UUID
        name = "Japanese",
        imageRes = R.drawable.img9
    ),
    Category(
        id = java.util.UUID.randomUUID().toString(), // Assign random UUID
        name = "Mexican",
        imageRes = R.drawable.img5
    ),
    Category(
        id = java.util.UUID.randomUUID().toString(), // Assign random UUID
        name = "American",
        imageRes = R.drawable.img10
    ),
    Category(
        id = java.util.UUID.randomUUID().toString(), // Assign random UUID
        name = "Mediterranean",
        imageRes = R.drawable.img1
    ),
    Category(
        id = java.util.UUID.randomUUID().toString(), // Assign random UUID
        name = "Vegan",
        imageRes = R.drawable.img7
    ),
    Category(
        id = java.util.UUID.randomUUID().toString(), // Assign random UUID
        name = "BBQ",
        imageRes = R.drawable.chinese
    )
    )


    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { category ->

            Box(
                modifier = Modifier
                    .size(width = 90.dp, height = 110.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(30.dp))
                    .background(Color.White, shape = RoundedCornerShape(30.dp))
                    .clickable {
                        println("Category IDdddddddddddddddddd: ${category.name}") // Example of using the ID
                        navController.navigate("categories/${category.name}")
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = category.imageRes),
                        contentDescription = "${category.name} Image",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(30.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = category.name,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF333A73)
                        )
                    )
                }
            }
        }
    }
}




@Composable
fun NearRestaurantsList(navController: NavController) {
    var restaurantList by remember { mutableStateOf<List<Restaurant>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitInstance.api.getRestaurants()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        restaurantList = responseBody  // Assign the list of restaurants to the state
                        Log.d("NearRestaurants", "Data fetched successfully: $restaurantList")
                    } else {
                        Log.e("NearRestaurants", "Empty response body")
                    }
                } else {
                    Log.e("NearRestaurants", "Error fetching data: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("NearRestaurants", "Error: ${e.localizedMessage}")
            }
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 1.dp, vertical = 12.dp),
        modifier = Modifier
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .padding(bottom = 50.dp)
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp)
    ) {
        items(restaurantList) { restaurant ->
            Card(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clickable { navController.navigate("restaurantMenu/${restaurant.id}") },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(3.dp) // Add shadow
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp), // Adjust height for consistency
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Assuming the image is stored in a drawable or URL for each restaurant
                    val imageRes = R.drawable.drink // Replace with actual logic to load image
                    RestaurantMenuItemImage(restaurant.logo)

                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp), // Adds spacing between lines
                        modifier = Modifier.padding(vertical = 8.dp) // Adds vertical padding
                    ) {
                        Text(
                            text = restaurant.name,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF333A73)
                            )
                        )
                        Text(
                            text = restaurant.address,
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        )
                        Text(
                            text = "⭐ ${restaurant.average_rating}",
                            style = TextStyle(
                                color = Color(0xFFFFA500),
                                fontSize = 12.sp
                            )
                        )
                        Text(
                            text = "📞 ${restaurant.contact_phone}",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }
        }
    }
}




data class Category(
    val id: String, // Add an ID field
    val name: String,
    val imageRes: Int
)

@Composable
fun MyBottomNavigation(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0) }
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Notifications,
        Icons.Filled.ShoppingCart,
        Icons.Filled.Person
    )

    NavigationBar(
        containerColor = Color(0xFFFE8C00),
        modifier = Modifier
            .height(56.dp) // Reduced height
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)) // Apply rounded corners
    ) {
        icons.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (selectedItem == index) Color.Blue else Color.White
                    )
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    val recentSearches = remember { mutableStateListOf("Subway", "Burgers", "Sandwich", "Pizza", "Fried Rice with meat", "Bakery", "Cake", "Cookies") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Search Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon", tint = Color.Gray)
                },
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White, // White background for search bar
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )

            if (searchText.isNotEmpty()) {
                TextButton(onClick = { searchText = "" }) {
                    Text("Cancel", color = Color(0xFF666666))
                }
            }
        }

        // Recent Searches Header
        Text(
            text = "RECENT SEARCHES",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Recent Searches List
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(recentSearches) { search ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { searchText = search }
                        .background(Color.White, shape = RoundedCornerShape(10.dp)) // White background for boxes
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = search,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    )
                }
            }
        }

        // Clear All Button
        TextButton(
            onClick = { recentSearches.clear() },
            modifier = Modifier.align(Alignment.End).padding(bottom=100.dp)
        ) {
            Text("CLEAR ALL", color = Color.Red)
        }
    }
}




@Composable
fun CategoriesScreen(id : String, navController: NavController) {
    var restaurantList by remember { mutableStateOf<List<Restaurant>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 8.dp)
    ) {
        DisplayRestaurantImage2(category = id)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
        }

        LaunchedEffect(Unit) {
            coroutineScope.launch {
                try {
                    val response = RetrofitInstance.api.getRestaurantByType(id)
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            restaurantList =
                                responseBody  // Assign the list of restaurants to the state
                            Log.d("NearRestaurants", "Data fetched successfully: $restaurantList")
                        } else {
                            Log.e("NearRestaurants", "Empty response body")
                        }
                    } else {
                        Log.e(
                            "NearRestaurants",
                            "Error fetching data: ${response.errorBody()?.string()}"
                        )
                    }
                } catch (e: Exception) {
                    Log.e("NearRestaurants", "Error: ${e.localizedMessage}")
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        ) {
            items(restaurantList) { restaurant ->
                Card(
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate("restaurantMenu/${restaurant.id}") },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(3.dp) // Add shadow
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp), // Adjust height for consistency
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Assuming the image is stored in a drawable or URL for each restaurant

                        RestaurantMenuItemImage(restaurant.logo)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp), // Adds spacing between lines
                            modifier = Modifier.padding(vertical = 8.dp) // Adds vertical padding
                        ) {
                            Text(
                                text = restaurant.name,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color(0xFF333A73)
                                )
                            )
                            Text(
                                text = restaurant.address,
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            )
                            Text(
                                text = "⭐ ${restaurant.average_rating}",
                                style = TextStyle(
                                    color = Color(0xFFFFA500),
                                    fontSize = 12.sp
                                )
                            )
                            Text(
                                text = "📞 ${restaurant.contact_phone}",
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun RestaurantDetailsScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Restaurant Details Screen",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}
@Composable
fun MoreRestaurantsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("More Restaurants Screen", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
    }
}

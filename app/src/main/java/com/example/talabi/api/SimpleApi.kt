package com.example.talabi.api


import com.example.talabi.Loginclass
import com.example.talabi.Menu
import com.example.talabi.OrderResponse
import com.example.talabi.Restaurant
import com.example.talabi.data.ApiResponse
import com.example.talabi.data.CartTotalResponse
import com.example.talabi.data.LoginRequest
import com.example.talabi.data.LoginResponse
import com.example.talabi.data.MenuItem
import com.example.talabi.data.MenuItems
import com.example.talabi.data.NotificationDto
import com.example.talabi.data.OrderItem
import com.example.talabi.data.Orders

import com.example.talabi.data.RemoveItemRequest
import com.example.talabi.data.RemoveItemResponse
import com.example.talabi.data.TotalResponse
import com.example.talabi.data.UpdateNotesRequest
import com.example.talabi.data.UpdateOrderItemResponse
import com.example.talabi.data.UpdateQuantityRequest
import com.example.talabi.data.User
import com.example.talabi.model.Post
import com.google.protobuf.Api
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.talabi.data.UserProfileRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.Part

interface SimpleApi {



    @GET("/api/users/1")
    suspend fun getPost(): Response<Post>

    @GET("/api/restaurants")
    suspend fun getRestaurants(): Response<List<Restaurant>>

    @GET("/api/menu_items")
    suspend fun getMenu(): Response<List<Menu>>


        @GET("/api/restaurants/{id}/menu_items")
        suspend fun getMenuItemsByRestaurantId(@Path("id") id: String): Response<List<Menu>>

    @GET("/api/restaurants/cuisine/{id}")
    suspend fun getRestaurantByType(@Path("id") cuisineType: String): Response<List<Restaurant>>



    @GET("/api/users/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<Post>




    @GET("/api/users")
    suspend fun getCustomPost(
        @Query("userId") userId:Int
    ): Response<List<Post>>

    @POST("/api/users")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>


    @FormUrlEncoded
    @POST("users")
    suspend fun pushPost2(
        @Field("name") name: String,
        @Field("id") id: Int,
        @Field("phone") phone: String,
        @Field("address") address: String
    ): Response<Post>

    @POST("/api/login")
    suspend fun loginUser(
        @Body logg: Loginclass
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("orders")
    suspend fun pushOrders(
        @Field("id") id: String,
        @Field("userId") userId: Int,
        @Field("restaurantId") restaurantId: String,
        @Field("deliveryAddress") deliveryAddress: String,
        @Field("status") status: String,
        @Field("totalPrice") totalPrice: String,
        @Field("deliveryNotes") deliveryNotes: String,
        @Field("createdAt") createdAt: String,
        @Field("updatedAt") updatedAt: String
    ): Response<Orders>
    @POST("/api/add-to-order")
    suspend fun addToOrder(@Body orderRequest: OrderItem): Response<OrderResponse>
    @GET("/api/order-items/{orderId}")
    suspend fun getOrderItems(@Path("orderId") orderId: Int): Response<List<MenuItems>>

    @PUT("/api/update-order-quantity")
    fun updateOrderQuantity(
        @Body request: UpdateQuantityRequest
    ): Call<ApiResponse>

    @PUT("/api/update-order-notes")
    fun updateOrderNotes(
        @Body request: UpdateNotesRequest
    ): Call<ApiResponse>
//    @DELETE("cart/remove/{orderItemId}")
//    suspend fun removeItemFromCart(@Path("orderItemId") orderItemId: Int): Response<ApiResponse>
//     @DELETE("cart/remove")
//     fun removeItemFromCart(@Body request: RemoveItemRequest): Call<ApiResponse>


    @DELETE("/api/cart/remove/{orderItemId}")
    suspend fun removeItemFromCart(@Path("orderItemId") orderItemId: Int): ApiResponse

    @GET("/api/cart/total/{orderId}")
    suspend fun getTotal(@Path("orderId") orderId: Int): TotalResponse
    @GET("/api/descriptionfood/{id}")
    suspend fun getMenuItem(@Path("id") menuItemId: Int): MenuItem
<<<<<<< Updated upstream
    @GET("/api/users/{userId}/notifications")
    suspend fun getNotifications(@Path("userId") userId: Int): Response<List<NotificationDto>>



// Update user profile
@Multipart
@PUT("/api/users/{id}")
suspend fun updateUserProfile(
    @Path("id") id: Int,
    @Part("name") name: RequestBody,
    @Part("email") email: RequestBody,

): Response<User>

    @GET("/api/users/{id}")
    suspend fun getUser(
        @Path("id") number: Int
    ): Response<User>

    @POST("/api/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>


=======



>>>>>>> Stashed changes
}
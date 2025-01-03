package com.example.talabi.api

import com.example.talabi.LoginResponse
import com.example.talabi.Loginclass
import com.example.talabi.Menu
import com.example.talabi.Restaurant
import com.example.talabi.model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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






}
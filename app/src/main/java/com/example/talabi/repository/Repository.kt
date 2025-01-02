package com.example.talabi.repository
import com.example.talabi.LoginResponse
import com.example.talabi.Loginclass
import com.example.talabi.Menu
import com.example.talabi.Restaurant
import com.example.talabi.api.RetrofitInstance

import com.example.talabi.model.Post
import retrofit2.Response
import retrofit2.Retrofit

class Repository {

    suspend fun login(logg: Loginclass): Response<LoginResponse> {
        return RetrofitInstance.api.loginUser(logg)
    }
    suspend fun getRestaurants(): Response<List<Restaurant>> {
        return RetrofitInstance.api.getRestaurants()
    }





    suspend fun  getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post> {
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPosts(userId: Int): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost(userId)
    }

    suspend fun pushPost(post: Post): Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushPost2(userId: String, id: Int, title: String, body: String): Response<Post> {
        return RetrofitInstance.api.pushPost2(userId, id, title, body)
    }
}
package com.example.talabi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.talabi.data.LoginResponse
import com.example.talabi.model.Post
import com.example.talabi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myRestaurant: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPosts: MutableLiveData<Response<List<Post>>> = MutableLiveData()


    val loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    fun login(logg: Loginclass) {
        viewModelScope.launch {
            val response = repository.login(logg)
            loginResponse.value = response
        }
    }



    fun pushPost2(userId: String, id: Int, title: String, body: String) {
        viewModelScope.launch {
            val response = repository.pushPost2(userId, id, title, body)
            myResponse.value = response
        }
    }

    fun getPost(){
        viewModelScope.launch { val response = repository.getPost()
        myResponse.value= response}
    }

//    fun getRestaurants(){
//        viewModelScope.launch { val response = repository.getRestaurants()
//        myRestaurant.value= response}
//    }
    fun getPost2(number: Int){
        viewModelScope.launch {
            val response = repository.getPost2(number)
        myResponse2.value= response}
    }
    fun getCustomPosts(userId: Int) {
        viewModelScope.launch {
            val response = repository.getCustomPosts(userId)
            myCustomPosts.value = response
        }
    }

}
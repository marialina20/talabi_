package com.example.talabi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.talabi.data.LoginResponse
import com.example.talabi.data.UserProfileRequest
import com.example.talabi.model.Post
import com.example.talabi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    // LiveData for the responses
    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myRestaurant: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPosts: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val userProfileResponse: MutableLiveData<UserProfileRequest> = MutableLiveData()
    val updateProfileStatus: MutableLiveData<String> = MutableLiveData() // For status messages
    val loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    // New LiveData to handle errors
    val errorResponse: MutableLiveData<String> = MutableLiveData()

    // Function to push a new post
    fun pushPost(post: Post) {
        viewModelScope.launch {
            try {
                val response = repository.pushPost(post)
                if (response.isSuccessful) {
                    myResponse.value = response
                } else {
                    errorResponse.value = "Failed to push post: ${response.message()}"
                }
            } catch (e: Exception) {
                errorResponse.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    // Function to log in
    fun login(logg: Loginclass) {
        viewModelScope.launch {
            try {
                val response = repository.login(logg)
                if (response.isSuccessful) {
                    loginResponse.value = response
                } else {
                    errorResponse.value = "Login failed: ${response.message()}"
                }
            } catch (e: Exception) {
                errorResponse.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    // Function to push a post with specific fields
    fun pushPost2(userId: String, id: Int, title: String, body: String) {
        viewModelScope.launch {
            try {
                val response = repository.pushPost2(userId, id, title, body)
                if (response.isSuccessful) {
                    myResponse.value = response
                } else {
                    errorResponse.value = "Failed to push post: ${response.message()}"
                }
            } catch (e: Exception) {
                errorResponse.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    // Function to get all posts
    fun getPost() {
        viewModelScope.launch {
            try {
                val response = repository.getPost()
                if (response.isSuccessful) {
                    myResponse.value = response
                } else {
                    errorResponse.value = "Failed to fetch posts: ${response.message()}"
                }
            } catch (e: Exception) {
                errorResponse.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    // Function to get specific post by ID
    fun getPost2(number: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getPost2(number)
                if (response.isSuccessful) {
                    myResponse2.value = response
                } else {
                    errorResponse.value = "Failed to fetch post: ${response.message()}"
                }
            } catch (e: Exception) {
                errorResponse.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    // Function to get custom posts based on user ID
    fun getCustomPosts(userId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getCustomPosts(userId)
                if (response.isSuccessful) {
                    myCustomPosts.value = response
                } else {
                    errorResponse.value = "Failed to fetch custom posts: ${response.message()}"
                }
            } catch (e: Exception) {
                errorResponse.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    // Function to update user profile
//    fun updateUserProfile(id: Int, userProfile: UserProfileRequest) {
//        viewModelScope.launch {
//            try {
//                val response = repository.updateUserProfile(id, userProfile)
//                if (response.isSuccessful) {
//                    userProfileResponse.value = userProfile
//                    updateProfileStatus.value = "Profile updated successfully"
//                } else {
//                    updateProfileStatus.value = "Failed to update profile: ${response.message()}"
//                }
//            } catch (e: Exception) {
//                updateProfileStatus.value = "Error: ${e.localizedMessage}"
//            }
//        }
//    }
}

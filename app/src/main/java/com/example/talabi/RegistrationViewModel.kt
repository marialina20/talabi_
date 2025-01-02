package com.example.talabi


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.talabi.model.Post
import com.example.talabi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class RegistrationViewModel(private val repository: Repository) : ViewModel() {

    var registrationResponse: Response<Post>? = null

    fun registerUser(post: Post, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.pushPost(post)
                if (response.isSuccessful) {
                    registrationResponse = response
                    onSuccess()
                } else {
                    onError("Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Exception: ${e.localizedMessage}")
            }
        }
    }
}

package com.example.talabi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.talabi.repository.Repository

class MainViewModelFactory(private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
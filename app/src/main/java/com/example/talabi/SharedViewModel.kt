package com.example.talabi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _orderId = MutableLiveData<Int>()
    private val _menuItemid = MutableLiveData<Int>()

    // Use a default value of 0 if _orderId is null
    val orderId: Int
        get() = _orderId.value ?: 0

    val menuItemid: LiveData<Int> get() = _menuItemid

    // Method to set the orderId
    fun setOrderId(id: Int) {
        _orderId.value = id
    }
}
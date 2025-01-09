package com.example.talabi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _orderId = MutableLiveData<Int>()
    private val _menuItemid = MutableLiveData<Int>()

    val orderId: LiveData<Int> get() = _orderId
    val menuItemid: LiveData<Int> get() = _menuItemid

    // Méthode pour définir l'orderId
    fun setOrderId(id: Int) {
        _orderId.value = id
    }

}

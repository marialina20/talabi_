package com.example.talabi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    private val _orderId = MutableLiveData<Int>(0)
    private val _menuItemId = MutableLiveData<Int>()
    private val _userIdd = MutableLiveData<Int>(0)
    private val _orderConfirmed = MutableLiveData<Boolean>(false)
    private val _orderCreated = MutableLiveData<Boolean>(false)
    private val _LoggedIn = MutableLiveData<Boolean>(false)



    val orderId: Int
        get() = _orderId.value!!
    val orderConfirmed: Boolean
        get() = _orderConfirmed.value!!
    val orderCreated: Boolean
        get() = _orderCreated.value!!
    val loggedIn: Boolean
        get() = _LoggedIn.value!!

    // Return nullable Int as LiveData
    val menuItemId: LiveData<Int>
        get() = _menuItemId

    val UserIdd: Int
        get() = _userIdd.value!!

    // Method to set the orderId
    fun setOrderId(id: Int) {
        _orderId.value = id
    }
fun setUserId(id: Int) {
        _userIdd.value = id
    }

    fun setOrderConfirmed(iss: Boolean) {
        _orderConfirmed.value = iss
    }
    fun setOrderCreated(iss: Boolean) {
        _orderConfirmed.value = iss
    }
    fun setLoggedIn(iss: Boolean) {
        _LoggedIn.value = iss
    }

    // Method to set the menuItemId
    fun setMenuItemId(id: Int) {
        _menuItemId.value = id
    }
}
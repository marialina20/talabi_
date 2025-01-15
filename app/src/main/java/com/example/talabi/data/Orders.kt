package com.example.talabi.data

import java.util.Date

data class OrderItem(
    val userId: Int,
    val menuItemId: Int,
    val quantity: Int,
    val specialNotes: String? = null
)
data class Orders(
    val id: Int? = null,
    val user_id: Int,
    val restaurant_id: Int,
    val delivery_address: String,
    val status: String,
    val total_price: Double,
    val delivery_notes: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)
data class UpdateOrderRequestt(
    val delivery_address: String? = null,
    val delivery_notes: String? = null
)

data class UpdateOrderResponsee(
    val message: String
)
enum class OrderStatus(val value: String) {
    PENDING("pending"),
    PREPARING("preparing"),
    ON_THE_WAY("on_the_way"),
    DELIVERED("delivered"),
    CANCELLED("cancelled");

    companion object {
        fun from(value: String): OrderStatus {
            return values().firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("Unknown status: $value")
        }
    }
}
data class CartTotalResponse(
    val total: Double,
    val id: Int? = null,
    val userId: Int,
    val restaurantId: Int,
    val deliveryAddress: String,
    val status: OrderStatus,
    val totalPrice: Double,
    val deliveryNotes: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
data class TotalResponse(
    val total: Double
)

package com.example.talabi.data

data class OrderItem(
    val userId: Int,         // Pas `orderId` !
    val menuItemId: Int,
    val quantity: Int,
    val specialNotes: String? = null // Notes spéciales, optionnel
)
data class Orders(
    val id: Int? = null, // Nullable car il est auto-incrémenté par la base de données
    val user_d: Int,     // Clé étrangère vers la table users
    val restaurant_id: Int, // Clé étrangère vers la table restaurants
    val delivery_address: String, // Adresse de livraison
    val status: OrderStatus, // Énumération pour le statut
    val total_price: Double,  // Prix total de la commande
    val delivery_notes: String? = null, // Notes de livraison, optionnel
    val created_at: String? = null, // Timestamp de création
    val updated_at: String? = null  // Timestamp de mise à jour
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

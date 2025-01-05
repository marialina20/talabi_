package com.example.talabi.data

data class OrderItem(
    val id: Int? = null, // Nullable, car il est auto-incrémenté par la base de données
    val orderId: Int,    // Clé étrangère vers la table orders
    val menuItemId: Int, // Clé étrangère vers la table menu_items
    val quantity: Int,   // Quantité de l'article commandé
    val specialNotes: String? = null // Notes spéciales, optionnel
)
data class Orders(
    val id: Int? = null, // Nullable car il est auto-incrémenté par la base de données
    val userId: Int,     // Clé étrangère vers la table users
    val restaurantId: Int, // Clé étrangère vers la table restaurants
    val deliveryAddress: String, // Adresse de livraison
    val status: OrderStatus, // Énumération pour le statut
    val totalPrice: Double,  // Prix total de la commande
    val deliveryNotes: String? = null, // Notes de livraison, optionnel
    val createdAt: String? = null, // Timestamp de création
    val updatedAt: String? = null  // Timestamp de mise à jour
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
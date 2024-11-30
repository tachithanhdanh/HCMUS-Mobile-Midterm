package com.danh.midterm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.danh.midterm.mock.MockData
import com.danh.midterm.model.CartItem
import com.danh.midterm.model.Order
import com.danh.midterm.model.Profile
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderViewModel: ViewModel() {
    // Hold the order list
    var orders by mutableStateOf<List<Order>>(MockData.orders)
        private set

    // Add an order to the list
    fun addOrder(order: Order) {
        orders = orders + order
    }

    // Remove an order from the list
    fun removeOrder(order: Order) {
        orders = orders - order
    }

    // Update the order list
    fun updateOrders(newOrders: List<Order>) {
        orders = newOrders.toMutableList()
    }

    fun createOrder(cartItem: CartItem, profile: Profile) {
        val order = Order(
            date = Date(),
            coffeeId = cartItem.coffeeId,
            totalAmount = cartItem.totalAmount,
            name = cartItem.name,
            address = profile.address,
            complete = mutableStateOf(false)
        )
        addOrder(order)
    }
}
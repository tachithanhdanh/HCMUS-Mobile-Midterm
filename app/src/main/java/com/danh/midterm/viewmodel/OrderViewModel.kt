package com.danh.midterm.viewmodel

import androidx.lifecycle.ViewModel
import com.danh.midterm.model.Order

class OrderViewModel: ViewModel() {
    // Hold the order list
    var orders = mutableListOf<Order>()
        private set

    // Add an order to the list
    fun addOrder(order: Order) {
        orders.add(order)
    }

    // Remove an order from the list
    fun removeOrder(order: Order) {
        orders.remove(order)
    }

    // Update the order list
    fun updateOrders(newOrders: List<Order>) {
        orders = newOrders.toMutableList()
    }
}
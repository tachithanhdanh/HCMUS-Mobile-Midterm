package com.danh.midterm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.danh.midterm.mock.MockData
import com.danh.midterm.model.CartItem

class CartViewModel : ViewModel() {
    // Giữ trạng thái của giỏ hàng
    var cartItems by mutableStateOf<List<CartItem>>(MockData.cartItems)
        private set

    // Hàm thêm sản phẩm vào giỏ hàng
    fun addItem(item: CartItem) {
        cartItems = cartItems + item
    }

    // Hàm xóa sản phẩm khỏi giỏ hàng
    fun removeItemById(id: Int) {
        cartItems = cartItems.filter { it.id != id }
    }

    // Hàm cập nhật giỏ hàng nếu cần
    fun updateCart(newItems: List<CartItem>) {
        cartItems = newItems
    }

    fun clearCart() {
        cartItems = emptyList()
    }

    fun getNewId(): Int {
        // Get id max + 1
        return cartItems.maxByOrNull { it.id }?.id?.plus(1) ?: 1
    }
}

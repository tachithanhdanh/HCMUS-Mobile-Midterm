package com.danh.midterm.model

import androidx.annotation.DrawableRes

data class CartItem(
    val id: Int,
    @DrawableRes val image: Int,
    val name: String,
    val shot: String,
    val select: String,
    val size: String,
    val ice: String,
    val quantity: Int,
    val totalAmount: Double
)
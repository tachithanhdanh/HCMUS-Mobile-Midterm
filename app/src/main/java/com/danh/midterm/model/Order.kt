package com.danh.midterm.model

import java.util.Date

data class Order(
    val date: Date,
    val totalAmount: Double,
    val name: String,
    val address: String,
    var complete: Boolean = false
)


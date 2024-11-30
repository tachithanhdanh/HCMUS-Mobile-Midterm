package com.danh.midterm.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Order(
    val date: Date,
    val totalAmount: Double,
    val coffeeId: Int,
    val name: String,
    val address: String,
    val complete: MutableState<Boolean> = mutableStateOf(false) // Thay đổi complete thành MutableState
) {
    fun getFormattedDate(): String {
        // Define the format
        val formatter = SimpleDateFormat("dd MMM yyyy | HH:mm", Locale.getDefault())

        // Convert the date to the desired format
        val formattedDate = formatter.format(date)

        return formattedDate
    }
}


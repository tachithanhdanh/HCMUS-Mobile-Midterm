package com.danh.midterm.model

import java.util.Date

data class RewardItem(
    val name: String,
    val date: Date,
    val points: Int
) {
    fun getFormattedDate(): String {
        // Define the format
        val formatter = java.text.SimpleDateFormat("dd MMM | hh:mm", java.util.Locale.getDefault())

        // Convert the date to the desired format
        val formattedDate = formatter.format(date)

        return formattedDate
    }
}
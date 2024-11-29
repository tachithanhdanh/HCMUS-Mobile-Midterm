package com.danh.midterm.model

import java.time.LocalDate

data class CoffeeRedeem(
    val id: Int,
    val coffeeId: Int,
    val validDate: LocalDate,
    val redeemPoint: Int,
    val imageResource: Int,
)
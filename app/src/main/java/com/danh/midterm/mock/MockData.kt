package com.danh.midterm.mock

import com.danh.midterm.R
import com.danh.midterm.model.CartItem
import com.danh.midterm.model.Coffee
import com.danh.midterm.model.CoffeeRedeem
import com.danh.midterm.model.Profile
import java.time.LocalDate

object MockData {
    val coffeeList = listOf(
        Coffee(1, "Americano", 3.5, R.drawable.img_americano),
        Coffee(2, "Cappuccino", 4.0, R.drawable.img_cappuccino),
        Coffee(3, "Mocha", 3.0, R.drawable.img_mocha),
        Coffee(4, "Flat White", 5.0, R.drawable.img_flat_white)
    )

    val CoffeeRedeemList = listOf(
        CoffeeRedeem(1, 1, LocalDate.of(2024, 12, 25), 1230, R.drawable.img_americano),
        CoffeeRedeem(2, 2, LocalDate.of(2024, 12, 25), 125, R.drawable.img_cappuccino),
        CoffeeRedeem(3, 3, LocalDate.of(2024, 12, 25), 2550, R.drawable.img_mocha),
        CoffeeRedeem(4, 4, LocalDate.of(2024, 12, 25), 215, R.drawable.img_flat_white)
    )

    val CurrentProfile = Profile(
        "Danh",
        "0123456789",
        "tachithanhdanh@gmail.com",
        "Ho Chi Minh city, Vietnam"
    )

    val cartItems = listOf(
        CartItem(
            1,
            R.drawable.img_americano,
            "Americano",
            "single",
            "iced",
            "medium",
            "full ice",
            1,
            20.0
        ),
        CartItem(
            2,
            R.drawable.img_cappuccino,
            "Cappuccino",
            "single",
            "iced",
            "medium",
            "full ice",
            2,
            40.0
        ),
        CartItem(
            3,
            R.drawable.img_mocha,
            "Mocha",
            "single",
            "iced",
            "medium",
            "full ice",
            3,
            60.0
        ),
    )
}
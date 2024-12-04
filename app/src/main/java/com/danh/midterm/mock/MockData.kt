package com.danh.midterm.mock

import androidx.compose.runtime.mutableStateOf
import com.danh.midterm.R
import com.danh.midterm.model.CartItem
import com.danh.midterm.model.Coffee
import com.danh.midterm.model.CoffeeRedeem
import com.danh.midterm.model.Order
import com.danh.midterm.model.Profile
import java.time.LocalDate
import java.util.Date

object MockData {
    val coffeeList = listOf(
        Coffee(
            id = 1,
            name = "Americano",
            price = 3.5,
            imageResource = R.drawable.img_americano,
            rewardPoint = 15,
            description = "A classic espresso-based drink with hot water, creating a smooth, rich flavor."
        ),
        Coffee(
            id = 2,
            name = "Cappuccino",
            price = 4.0,
            imageResource = R.drawable.img_cappuccino,
            rewardPoint = 6,
            description = "Espresso topped with steamed milk and a layer of foamy milk, creating a creamy texture."
        ),
        Coffee(
            id = 3,
            name = "Mocha",
            price = 3.0,
            imageResource = R.drawable.img_mocha,
            rewardPoint = 18,
            description = "A sweet combination of espresso, steamed milk, and chocolate syrup, topped with whipped cream."
        ),
        Coffee(
            id = 4,
            name = "Flat White",
            price = 5.0,
            imageResource = R.drawable.img_flat_white,
            rewardPoint = 24,
            description = "A smooth, velvety coffee made with a double shot of espresso and steamed milk."
        )
    )

    val coffeeRedeemList = listOf(
        CoffeeRedeem(1, 1, LocalDate.of(2024, 12, 25), 1230, R.drawable.img_americano),
        CoffeeRedeem(2, 2, LocalDate.of(2024, 12, 25), 125, R.drawable.img_cappuccino),
        CoffeeRedeem(3, 3, LocalDate.of(2024, 12, 25), 2550, R.drawable.img_mocha),
        CoffeeRedeem(4, 4, LocalDate.of(2024, 12, 25), 215, R.drawable.img_flat_white)
    )

    val cartItems = listOf(
        CartItem(
            1,
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

    val orders = listOf(
        Order(
            Date(),
            20.0,
            1,
            coffeeList.find { it.id == 1 }!!.name,
            "3 Addreson Court United State",
            mutableStateOf(false)
        ),
        Order(
            Date(),
            40.0,
            2,
            coffeeList.find { it.id == 2 }!!.name,
            "3 Addreson Court United State",
            mutableStateOf(false)
        ),
        Order(
            Date(),
            60.0,
            3,
            coffeeList.find { it.id == 3 }!!.name,
            "3 Addreson Court United State",
            mutableStateOf(false)
        ),
        Order(
            Date(),
            80.0,
            4,
            coffeeList.find { it.id == 4 }!!.name,
            "3 Addreson Court United State",
            mutableStateOf(true)
        ),
        Order(
            Date(),
            100.0,
            2,
            coffeeList.find { it.id == 4 }!!.name,
            "3 Addreson Court United State",
            mutableStateOf(true)
        ),
    )

    val profile = Profile(
        "Danh",
        "0123456789",
        "tachithanhdanh@gmail.com",
        "Ho Chi Minh city, Vietnam",
        points = orders.sumOf { order ->
            // Tìm coffee tương ứng với order
            val coffee = coffeeList.find { it.id == order.coffeeId }
            val coffeePoint =
                (coffee?.rewardPoint?.times((order.totalAmount / (coffee.price)).toInt())) ?: 0
            return@sumOf coffeePoint
        }
    )
}
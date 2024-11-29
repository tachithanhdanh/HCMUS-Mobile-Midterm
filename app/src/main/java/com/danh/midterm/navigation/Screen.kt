package com.danh.midterm.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object CoffeeDetail : Screen("coffee_detail")
    data object CoffeeCart : Screen("coffee_cart")
    data object OrderSuccess : Screen("order_success")
    data object Profile : Screen("profile")
    data object Rewards : Screen("rewards")
    data object RedeemRewards : Screen("redeem_rewards")
    data object MyOrders : Screen("my_orders")
}

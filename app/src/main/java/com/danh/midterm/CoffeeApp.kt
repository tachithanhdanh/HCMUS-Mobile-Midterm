package com.danh.midterm

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

enum class CoffeeScreen(@StringRes val title: Int) {
    SplashScreen(title = R.string.splash_screen),
    HomeScreen(title = R.string.home_screen),
    CoffeeDetailScreen(title = R.string.coffee_detail_screen),
    CoffeeCartScreen(title = R.string.coffee_cart_screen),
    ProfileScreen(title = R.string.profile_screen),
    RewardsScreen(title = R.string.rewards_screen),
    RedeemRewardsScreen(title = R.string.redeem_rewards_screen),
    MyOrdersScreen(title = R.string.my_orders_screen),
}

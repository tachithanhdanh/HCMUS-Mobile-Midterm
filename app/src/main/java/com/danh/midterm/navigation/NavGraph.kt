// midterm/navigation/NavGraph.kt
package com.danh.midterm.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danh.midterm.R
import com.danh.midterm.mock.MockData
import com.danh.midterm.navigation.Screen.CoffeeDetail
import com.danh.midterm.navigation.Screen.Home
import com.danh.midterm.navigation.Screen.Splash
import com.danh.midterm.ui.screens.CoffeeCartScreen
import com.danh.midterm.ui.screens.CoffeeDetailScreen
import com.danh.midterm.ui.screens.MyOrdersScreen
import com.danh.midterm.ui.screens.OrderSuccessScreen
import com.danh.midterm.ui.screens.ProfileScreen
import com.danh.midterm.ui.screens.RedeemRewardsScreen
import com.danh.midterm.ui.screens.RewardsScreen
import com.danh.midterm.ui.screens.SplashScreen
import com.danh.midterm.viewmodel.CartViewModel
import com.danh.midterm.viewmodel.CoffeeViewModel
import com.danh.midterm.viewmodel.OrderViewModel
import com.danh.midterm.viewmodel.ProfileViewModel
import com.example.ordercoffee.ui.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val cartViewModel: CartViewModel = viewModel() // Lấy
    val cartItems = cartViewModel.cartItems
    val orderViewModel: OrderViewModel = viewModel()
    val profileViewModel: ProfileViewModel = viewModel()
    val currentProfile = profileViewModel.profile
    val coffeeViewModel: CoffeeViewModel = viewModel()
    NavHost(navController = navController, startDestination = Splash.route) {
        composable(Splash.route) {
            SplashScreen(
                navController = navController,
                backgroundImageResource = R.drawable.background,
                logoImageResource = R.drawable.logo
            )
        }
        composable(Home.route) {
            HomeScreen(
                navController = navController,
                coffeeViewModel = coffeeViewModel,
                onCoffeeSelected = { coffeeId ->
                    navController.navigate(CoffeeDetail.route + "/$coffeeId")
                },
                onCartClick = { navController.navigate(Screen.CoffeeCart.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                fullName = currentProfile.name
            )
        }
        composable(CoffeeDetail.route + "/{coffeeId}?isRedeem={isRedeem}") { backStackEntry ->
            val coffeeId = backStackEntry.arguments?.getString("coffeeId")?.toIntOrNull() ?: 1
            val isRedeem = backStackEntry.arguments?.getString("isRedeem").equals("true")
            Log.d("CoffeeDetail", backStackEntry.arguments.toString())
            Log.d("CoffeeDetail", "isRedeem: $isRedeem")
            CoffeeDetailScreen(
                navController = navController, coffeeId = coffeeId,
                coffeeViewModel = coffeeViewModel,
                onAddToCart = { cartViewModel.addItem(it); navController.navigate(Screen.CoffeeCart.route) },
                onBack = { navController.popBackStack() },
                isRedeem = isRedeem
            )
        }
        composable(Screen.CoffeeCart.route) {
            CoffeeCartScreen(
                navController = navController,
                cartViewModel = cartViewModel,
                onCheckout = {
                    cartItems.forEach() {
                        orderViewModel.createOrder(it, currentProfile)
                    }
                    cartViewModel.clearCart()
                    for (order in orderViewModel.orders) {
                        Log.d("Order", order.toString())
                    }
                    navController.navigate(Screen.OrderSuccess.route)
                },
                onBack = { navController.popBackStack() },
                onDelete = { id ->
                    cartViewModel.removeItemById(id)
                })
        }
        composable(Screen.OrderSuccess.route) {
            OrderSuccessScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.Rewards.route) {
            RewardsScreen(
                navController = navController,
                coffeeViewModel = coffeeViewModel,
                profileViewModel = profileViewModel,
                orderViewModel = orderViewModel,
                onRedeemClick = { navController.navigate(Screen.RedeemRewards.route) }
            )
        }
        composable(Screen.RedeemRewards.route) {
            RedeemRewardsScreen(
                navController = navController,
                coffeeViewModel = coffeeViewModel,
                coffeeRedeemList = MockData.coffeeRedeemList,
                onRedeemReward = {
                    if (currentProfile.points >= it.redeemPoint) {
                        profileViewModel.updateProfile(currentProfile.copy(points = currentProfile.points - it.redeemPoint))
                        navController.navigate(Screen.CoffeeDetail.route + "/${it.coffeeId}?isRedeem=true")
                    }
                })
        }
        composable(Screen.MyOrders.route) {
            MyOrdersScreen(
                navController = navController,
                orderViewModel = orderViewModel,
                coffeeViewModel = coffeeViewModel
            )
        }
    }
}

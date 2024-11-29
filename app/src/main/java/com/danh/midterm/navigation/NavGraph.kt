// midterm/navigation/NavGraph.kt
package com.danh.midterm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.danh.midterm.ui.screens.SplashScreen
import com.danh.midterm.viewmodel.CartViewModel
import com.example.ordercoffee.ui.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    var currentProfile by remember { mutableStateOf(MockData.CurrentProfile) }
    val cartViewModel: CartViewModel = viewModel() // Lấy
    val cartItems = cartViewModel.cartItems
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
                onCoffeeSelected = { coffeeId ->
                    navController.navigate(CoffeeDetail.route + "/$coffeeId")
                },
                onCartClick = { navController.navigate(Screen.CoffeeCart.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                fullName = currentProfile.fullName
            )
        }
        composable(CoffeeDetail.route + "/{coffeeId}") { backStackEntry ->
            val coffeeId = backStackEntry.arguments?.getString("coffeeId")?.toIntOrNull() ?: 1
            CoffeeDetailScreen(
                navController = navController, coffeeId = coffeeId,
                onAddToCart = { cartViewModel.addItem(it); navController.navigate(Screen.CoffeeCart.route) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.CoffeeCart.route) {
            CoffeeCartScreen(
                navController = navController,
                cartViewModel = cartViewModel,
                onCheckout = { navController.navigate(Screen.OrderSuccess.route) },
                onBack = { navController.popBackStack() },
                onDelete = { id ->
                    cartViewModel.removeItemById(id)
                })
        }
        composable(Screen.OrderSuccess.route) {
            OrderSuccessScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController, profile = currentProfile)
        }
//        composable(Rewards.route) {
//            RewardsScreen(navController = navController)
//        }
//        composable(RedeemRewards.route) {
//            RedeemRewardsScreen(navController = navController)
//        }
        composable(Screen.MyOrders.route) {
            MyOrdersScreen(navController = navController)
        }
    }
}

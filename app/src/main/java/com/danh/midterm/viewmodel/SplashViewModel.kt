package com.danh.midterm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.danh.midterm.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    // Có thể thêm LiveData hoặc State để theo dõi trạng thái chuyển hướng
    fun navigateToHome(navController: NavHostController) {
        viewModelScope.launch {
            delay(2000) // Thời gian hiển thị Splash
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }
}

package com.example.ordercoffee.ui.components

import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.danh.midterm.R
import com.danh.midterm.navigation.Screen
import java.util.logging.Logger

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp,
        modifier = Modifier.clip(RoundedCornerShape(16.dp)) // Bo góc cho NavigationBar
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate(Screen.Home.route)
            },
            icon = {
                Icon(
                    painter = painterResource(
                        id =
                        if (currentRoute.equals(Screen.Home.route))
                            R.drawable.icon_home_selected else R.drawable.icon_home_unselected
                    ),
                    contentDescription = "Home",
                    tint = Color.Unspecified
                )
            },
            alwaysShowLabel = false, // Hide text
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Screen.Rewards.route)
            },
            icon = {
                Icon(
                    painter = painterResource(
                        id = if (currentRoute.equals(Screen.Rewards.route))
                            R.drawable.icon_gift_selected else R.drawable.icon_gift_unselected
                    ),
                    contentDescription = "Rewards",
                    tint = Color.Unspecified
                )
            },
            alwaysShowLabel = false, // Hide text
            // https://stackoverflow.com/a/77528930
            // Hide color when selected
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Screen.MyOrders.route)
            },
            icon = {
                Icon(
                    painter = painterResource(
                        id = if (currentRoute.equals(Screen.MyOrders.route))
                            R.drawable.icon_order_selected else R.drawable.icon_order_unselected
                    ),
                    contentDescription = "Orders",
                    tint = Color.Unspecified
                )
            },
            alwaysShowLabel = false, // Hide text
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}

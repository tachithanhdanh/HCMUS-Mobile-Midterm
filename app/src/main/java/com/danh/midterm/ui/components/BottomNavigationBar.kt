package com.example.ordercoffee.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danh.midterm.R

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp,
        modifier = Modifier.clip(RoundedCornerShape(16.dp)) // Bo góc cho NavigationBar
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { /* TODO */ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_home_selected),
                    contentDescription = "Home",
                    tint = Color.DarkGray
                )
            },
            alwaysShowLabel = false, // Hide text
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_gift_unselected),
                    contentDescription = "Rewards",
                    tint = Color.LightGray
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
            onClick = { /* TODO */ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_order_unselected),
                    contentDescription = "Orders",
                    tint = Color.LightGray
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
    BottomNavigationBar()
}

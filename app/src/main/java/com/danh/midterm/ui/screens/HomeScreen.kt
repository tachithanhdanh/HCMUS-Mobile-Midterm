package com.danh.midterm.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danh.midterm.R
import com.danh.midterm.ui.components.CoffeeOrderingUI
import com.danh.midterm.ui.components.LoyaltyCard
import com.danh.midterm.ui.theme.TextColor
import com.danh.midterm.viewmodel.CoffeeViewModel


@Composable
fun HomeScreen(
    navController: NavHostController,
    coffeeViewModel: CoffeeViewModel = viewModel(),
    onCoffeeSelected: (Int) -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit,
    fullName: String = "Danh",
) {
    // Thu thập dữ liệu từ StateFlow
    val coffeeList = coffeeViewModel.coffeeList

    // Entire Home Screen is wrapped in a Scaffold
    Scaffold(
    ) { padding ->
        // The column wraps all content in the Home Screen
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White)
        ) {
            Row {
                Column(
                    modifier = Modifier
//                            .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    // Header Section
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Good morning",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                            )
                            Text(
                                text = fullName,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextColor
                                )
                            )
                        }
                        Row {
                            IconButton(onClick = onCartClick) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_cart),
                                    contentDescription = "Cart",
                                    tint = Color.Black
                                )
                            }
                            IconButton(onClick = onProfileClick) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_profile),
                                    contentDescription = "Profile",
                                    tint = Color.Black
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Loyalty Card Section
                    LoyaltyCard(
                        currentStamps = 4,
                        maxStamps = 8
                    )

                    Spacer(modifier = Modifier.height(24.dp))


                }

            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CoffeeOrderingUI(
                    navController = navController,
                    coffeeList = coffeeList,
                    onCoffeeSelected = { onCoffeeSelected(it) }
                )
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(
        navController = navController,
        onCoffeeSelected = {},
        onCartClick = {},
        onProfileClick = {},
    )
}
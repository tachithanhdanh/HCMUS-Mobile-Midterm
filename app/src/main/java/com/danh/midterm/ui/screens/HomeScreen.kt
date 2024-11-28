package com.example.ordercoffee.ui.screens

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
import com.danh.midterm.R
import com.danh.midterm.ui.components.CoffeeOption
import com.danh.midterm.ui.components.CoffeeOrderingUI
import com.danh.midterm.ui.components.LoyaltyCard


@Composable
fun HomeScreen(
    onCoffeeSelected: (String) -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    // Entire Home Screen is wrapped in a Scaffold
    Scaffold(
        bottomBar = {
//            BottomNavigationBar()
        }
    ) { padding ->
        // The column wraps all content in the Home Screen
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
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
                                text = "Anderson",
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
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
                    coffeeOptions = listOf(
                        CoffeeOption("Americano", R.drawable.img_americano),
                        CoffeeOption("Cappuccino", R.drawable.img_cappuccino),
                        CoffeeOption("Mocha", R.drawable.img_mocha),
                        CoffeeOption("Flat White", R.drawable.img_flat_white)
                    ),
                    loyaltyCardProgress = 4,
                    onCoffeeSelected = {}
                )
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onCoffeeSelected = {},
        onCartClick = {},
        onProfileClick = {}
    )
}
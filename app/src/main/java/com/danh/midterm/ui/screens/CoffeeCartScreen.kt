package com.danh.midterm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danh.midterm.R
import com.danh.midterm.ui.components.SwipeableCoffeeCartItem
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.TextColor
import com.danh.midterm.viewmodel.CartViewModel

@Composable
fun CoffeeCartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = viewModel(),
    onCheckout: () -> Unit,
    onBack: () -> Unit = { navController.popBackStack() },
    onDelete: (Int) -> Unit
) {
    val cartItems = cartViewModel.cartItems
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row {
                        Text(
                            text = "Total Price",
                            style = TextStyle(fontSize = 16.sp, color = TextColor.copy(alpha = 0.25f))
                        )
                    }
                    Row {
                        Text(
                            text = "$%.2f".format(cartItems.sumOf { it.totalAmount }),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                                color = TextColor
                            )
                        )
                    }
                }

                Column {
                    Button(
                        modifier = Modifier
//                            .padding(16.dp)
                            .height(48.dp),
                        onClick = onCheckout,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkBlue,
                            contentColor = Color.White
                        ),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_cart),
                            contentDescription = "Cart",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Checkout",
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W600)
                        )
                    }
                }
            }
        },
        containerColor = Color.White
    ) {
        padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            // Header: back button and cart icon

            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            ) {
                Text(
                    text = "My Cart",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W500, color = TextColor)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
//                .padding(horizontal = 24.dp)
            ) {
                items(cartItems) { item ->
                    SwipeableCoffeeCartItem(
                        coffeeImage = item.image,
                        coffeeName = item.name,
                        options = "${item.shot} | ${item.select} | ${item.size} | ${item.ice}",
                        totalAmount = item.totalAmount,
                        quantity = item.quantity,
                        onDelete = { onDelete(item.id) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun CoffeeCartScreenPreview() {
    val navController = rememberNavController()
    CoffeeCartScreen(
        navController = navController,
        cartViewModel = CartViewModel(),
        onCheckout = { /* Handle checkout action */ },
        onDelete = { /* Handle delete action */ }
    )
}


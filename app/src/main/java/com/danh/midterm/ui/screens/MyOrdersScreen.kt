package com.danh.midterm.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danh.midterm.ui.components.OrderItem
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DividerColor
import com.danh.midterm.ui.theme.Gray
import com.danh.midterm.ui.theme.TextColor
import com.danh.midterm.viewmodel.CoffeeViewModel
import com.danh.midterm.viewmodel.OrderViewModel
import com.example.ordercoffee.ui.components.BottomNavigationBar

@Composable
fun MyOrdersScreen(
    navController: NavHostController,
    orderViewModel: OrderViewModel = viewModel(),
    coffeeViewModel: CoffeeViewModel = viewModel(),
) {
    var isHistory by remember { mutableStateOf(false) }
    val orders = orderViewModel.orders

    val disabledTextStyle = TextStyle(
        fontSize = 16.sp,
        color = Gray,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W500
    )
    val enabledTextStyle = TextStyle(
        fontSize = 16.sp,
        color = TextColor,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W500
    )

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .verticalScroll(scrollState),
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "My Orders", style = TextStyle(
                            fontSize = 16.sp,
                            color = TextColor,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W500
                        )
                    )


                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                            .clickable { isHistory = false },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "On going",
                            style = if (!isHistory) enabledTextStyle else disabledTextStyle
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(
                            thickness = 2.dp,
                            color = if (!isHistory) DarkBlue else DividerColor,
//                    modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                            .clickable { isHistory = true },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "History",
                            style = if (isHistory) enabledTextStyle else disabledTextStyle
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(
                            thickness = 2.dp,
                            color = if (isHistory) DarkBlue else DividerColor,
//                    modifier = Modifier.padding(vertical = 8.dp)
                        )

                    }
                }
                HorizontalDivider(
                    thickness = 2.dp,
                    color = DividerColor, // Màu nhạt
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(16.dp)) // Bo góc cho NavigationBar
                    .shadow(
                        elevation = 16.dp, shape = RoundedCornerShape(16.dp)
                    )
                    .background(Color.White) // Thêm hiệu ứng đổ bóng
            ) {
                BottomNavigationBar(
                    navController = navController,
                    modifier = Modifier.background(Color.White)
                )
            }
        },
        // https://stackoverflow.com/a/69144648
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 32.dp)
                    .verticalScroll(scrollState),
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        orders.filter { it.complete.value == isHistory }.forEach { order ->
                            OrderItem(
                                order = order
                            )
                        }

                    }

                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun MyOrderScreenPreview() {
    MyOrdersScreen(
        navController = rememberNavController(),
        orderViewModel = OrderViewModel()
    )
}
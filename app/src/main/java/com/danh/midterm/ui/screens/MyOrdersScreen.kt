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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danh.midterm.R
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DarkBlueLight
import com.danh.midterm.ui.theme.DividerColor
import com.danh.midterm.ui.theme.Gray
import com.danh.midterm.ui.theme.TextColor
import com.example.ordercoffee.ui.components.BottomNavigationBar

@Composable
fun MyOrdersScreen(
    navController: NavHostController,
    orders: List<Order>,
    isHistory: Boolean,
    onRedeemReward: (Order) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
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

        if (isHistory) {
            MyHistoryOrdersScreen(orders)
        } else {
            MyOngoingOrdersScreen(orders)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                orders.forEach { order ->
                    OrderItem(
                        order = order, onRedeemReward = onRedeemReward, disabled = isHistory
                    )
                }

            }

        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)) // Bo góc cho NavigationBar
                .shadow(
                    elevation = 12.dp, shape = RoundedCornerShape(16.dp)
                ) // Thêm hiệu ứng đổ bóng
        ) {
            BottomNavigationBar()
        }
    }
}

@Composable
fun MyOngoingOrdersScreen(
    orders: List<Order>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "On going", style = TextStyle(
                    fontSize = 16.sp,
                    color = TextColor,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W500
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                thickness = 2.dp,
                color = DarkBlue,
//                    modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "History", style = TextStyle(
                    fontSize = 16.sp,
                    color = Gray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W500
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                thickness = 2.dp,
                color = DividerColor,
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

@Composable
fun MyHistoryOrdersScreen(
    orders: List<Order>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "On going", style = TextStyle(
                    fontSize = 16.sp,
                    color = Gray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W500
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                thickness = 2.dp,
                color = DividerColor,
//                    modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "History", style = TextStyle(
                    fontSize = 16.sp,
                    color = TextColor,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W500
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                thickness = 2.dp,
                color = DarkBlue,
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

@Composable
fun OrderItem(
    order: Order,
    onRedeemReward: (Order) -> Unit,
    disabled: Boolean = false // Thêm biến trạng thái `disabled`
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !disabled) { onRedeemReward(order) } // Disable click nếu `disabled = true`
            .graphicsLayer(alpha = if (disabled) 0.5f else 1f), // Điều chỉnh độ mờ
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        )
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = order.date, style = TextStyle(
                            color = DarkBlueLight,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500
                        )
                    )
                }
                Column {
                    Text(
                        text = "$${order.price}", style = TextStyle(
                            color = DarkBlue,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(2.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_cup_my_order),
                    contentDescription = "Coffee",
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = order.item, style = TextStyle(
                        color = DarkBlue,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = "Location",
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = order.location, style = TextStyle(
                        color = DarkBlue,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500
                    )
                )
            }
            HorizontalDivider(
                thickness = 2.dp,
                color = DividerColor, // Màu nhạt
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

data class Order(
    val date: String,
    val item: String,
    val location: String,
    val price: Double
)

@Preview(showBackground = true)
@Composable
fun MyOrderScreenPreview() {
    MyOrdersScreen(
        navController = rememberNavController(),
        orders = listOf(
            Order(
                "24 June | 12:30 PM",
                "Americano",
                "3 Addersion Court Chino Hills, HO56824, United State",
                5.99
            ),
            Order(
                "24 June | 12:30 PM",
                "Cafe Latte",
                "3 Addersion Court Chino Hills, HO56824, United State",
                3.99
            ),
            Order(
                "24 June | 12:30 PM",
                "Flat White",
                "3 Addersion Court Chino Hills, HO56824, United State",
                4.99
            )
        ),
        isHistory = true
    ) { }
}
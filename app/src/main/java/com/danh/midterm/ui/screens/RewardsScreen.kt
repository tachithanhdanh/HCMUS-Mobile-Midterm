package com.danh.midterm.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.danh.midterm.model.RewardItem
import com.danh.midterm.ui.components.LoyaltyCard
import com.danh.midterm.ui.components.PointCard
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DarkBlueLight
import com.danh.midterm.ui.theme.DividerColor
import com.danh.midterm.viewmodel.CoffeeViewModel
import com.danh.midterm.viewmodel.OrderViewModel
import com.danh.midterm.viewmodel.ProfileViewModel
import com.example.ordercoffee.ui.components.BottomNavigationBar

@Composable
fun RewardsScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = viewModel(),
    orderViewModel: OrderViewModel = viewModel(),
    coffeeViewModel: CoffeeViewModel = viewModel(),
    onRedeemClick: () -> Unit = {},
) {
    val profile = profileViewModel.profile
    val orders = orderViewModel.orders
    val coffeeList = coffeeViewModel.coffeeList
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Rewards",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W500,
                        color = DarkBlue,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(24.dp)
                )
            }

        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(16.dp)) // Bo góc cho NavigationBar
                    .shadow(elevation = 12.dp, shape = RoundedCornerShape(16.dp)) // Thêm hiệu ứng đổ bóng
            ) {
                BottomNavigationBar(navController)
            }
        },
        containerColor = Color.Transparent
    ) {
        padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            LoyaltyCard(
                currentStamps = 4,
                maxStamps = 8
            )

            Spacer(modifier = Modifier.height(16.dp))

            PointCard(
                currentPoint = profile.points,
                onClick = onRedeemClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            HistoryRewards(
                // Truyền vào danh sách lịch sử giao dịch
                historyRewards = orders.map {
                    val coffee = coffeeViewModel.findCoffeeById(it.coffeeId)
                    val rewardPoint = coffee.rewardPoint.times((it.totalAmount / (coffee.price)).toInt())
                    RewardItem(
                        name = it.name,
                        date = it.date,
                        points = rewardPoint
                    )
                },
                modifier = Modifier.weight(1f)
            )
//
//        Spacer(modifier = Modifier.height(16.dp))
//

        }
    }

}

@Composable
fun HistoryRewards(historyRewards: List<RewardItem>, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
    ) {
        Text(
            text = "History Rewards",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                color = DarkBlue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(historyRewards) { reward ->
                RedeemItem(reward)
                HorizontalDivider(
                    thickness = 2.dp,
                    color = DividerColor, // Màu nhạt
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun RedeemItem(reward: RewardItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = reward.name,
                style = TextStyle(
                    color = DarkBlue,
                    fontWeight = FontWeight.W500,
                    fontSize = 12.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = reward.getFormattedDate(),
                style = TextStyle(
                    color = DarkBlueLight,
                    fontWeight = FontWeight.W500,
                    fontSize = 12.sp
                )
            )
        }
        Text(
            text = "+ ${reward.points} Pts",
            style = TextStyle(
                color = DarkBlue,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RewardsScreenPreview() {
    RewardsScreen(rememberNavController())
}
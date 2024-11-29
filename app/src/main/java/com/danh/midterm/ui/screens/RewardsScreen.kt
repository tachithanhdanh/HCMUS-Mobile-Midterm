package com.danh.midterm.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danh.midterm.navigation.Screen
import com.danh.midterm.ui.components.LoyaltyCard
import com.danh.midterm.ui.components.PointCard
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DarkBlueLight
import com.danh.midterm.ui.theme.DividerColor
import com.example.ordercoffee.ui.components.BottomNavigationBar

@Composable
fun RewardsScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        LoyaltyCard(
            currentStamps = 4,
            maxStamps = 8
        )

        Spacer(modifier = Modifier.height(16.dp))

        PointCard(
            currentPoint = 2750
        )

        Spacer(modifier = Modifier.height(16.dp))


        HistoryRewards(
            // Truyền vào danh sách lịch sử giao dịch
            historyRewards = listOf(
                RewardItem(
                    name = "Americano",
                    date = "24 June | 12:30 PM",
                    points = 12
                ),
                RewardItem(
                    name = "Cafe Latte",
                    date = "22 June | 08:30 AM",
                    points = 12
                ),
                RewardItem(
                    name = "Green Tea Latte",
                    date = "16 June | 10:48 AM",
                    points = 12
                ),
                RewardItem(
                    name = "Flat White",
                    date = "12 May | 11:25 AM",
                    points = 12
                ),
            ),
            modifier = Modifier.weight(1f)
        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)) // Bo góc cho NavigationBar
                .shadow(elevation = 12.dp, shape = RoundedCornerShape(16.dp)) // Thêm hiệu ứng đổ bóng
        ) {
            BottomNavigationBar(navController)
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
                RewardItem(reward)
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
fun RewardItem(reward: RewardItem) {
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
                text = reward.date,
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

data class RewardItem(
    val name: String,
    val date: String,
    val points: Int
)

@Preview(showBackground = true)
@Composable
fun RewardsScreenPreview() {
    RewardsScreen(rememberNavController())
}
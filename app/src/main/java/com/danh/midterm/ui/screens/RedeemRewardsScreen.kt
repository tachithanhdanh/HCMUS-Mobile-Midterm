package com.danh.midterm.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.R
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.TextColor

@Composable
fun RedeemRewardsScreen(
    rewards: List<Reward>,
    onRedeemReward: (Reward) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header: back button and cart icon
        Row(
            modifier = Modifier
                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle back */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
            // Spacer to push the Text to center
            Spacer(modifier = Modifier.weight(0.8f)) // Đẩy Text vào giữa
            Text(
                text = "Redeem",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = TextColor,
                    textAlign = TextAlign.Center
                )
            )
            // Empty space on the right to balance
            Spacer(modifier = Modifier.weight(1f)) // Đảm bảo Text vẫn ở giữa
        }
        rewards.forEach { reward ->
            RewardItem(
                reward = reward,
                onRedeemReward = onRedeemReward
            )
        }
    }
}

@Composable
fun RewardItem(
    reward: Reward,
    onRedeemReward: (Reward) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRedeemReward(reward) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
//        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(reward.imageResId),
                contentDescription = reward.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(reward.name)
                Text("Valid until ${reward.validUntil}", style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 2.dp),
                onClick = {  },
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkBlue,
                    contentColor = Color.White
                ),
            ) {
                Text(
                    "${reward.points} pts", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.White,
                    )
                )
            }

        }
    }
}

data class Reward(
    val name: String,
    val points: Int,
    val validUntil: String,
    val imageResId: Int
)

@Preview(showBackground = true)
@Composable
fun RewardItemPreview() {
    RedeemRewardsScreen(
        rewards = listOf(
            Reward("Americano", 1340, "04.07.21", R.drawable.img_americano),
            Reward("Flat White", 1340, "04.07.2021", R.drawable.img_flat_white),
            Reward("Cappuccino", 1340, "04.07.2021", R.drawable.img_cappuccino),
        )
    ) { }
}
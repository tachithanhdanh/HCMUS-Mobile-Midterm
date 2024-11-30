package com.danh.midterm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danh.midterm.R
import com.danh.midterm.mock.MockData
import com.danh.midterm.model.CoffeeRedeem
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.TextColor
import com.danh.midterm.viewmodel.CoffeeViewModel

@Composable
fun RedeemRewardsScreen(
    navController: NavHostController,
    coffeeViewModel: CoffeeViewModel = viewModel(),
    coffeeRedeemList: List<CoffeeRedeem>,
    onRedeemReward: (CoffeeRedeem) -> Unit
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
            IconButton(onClick = { navController.popBackStack() }) {
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
        coffeeRedeemList.forEach { redeem ->
            RedeemItem(
                coffeeViewModel = coffeeViewModel,
                coffeeRedeem = redeem,
                onRedeemReward = onRedeemReward
            )
        }
    }
}

@Composable
fun RedeemItem(
    coffeeViewModel: CoffeeViewModel,
    coffeeRedeem: CoffeeRedeem,
    onRedeemReward: (CoffeeRedeem) -> Unit
) {
    val coffee = coffeeViewModel.findCoffeeById(coffeeRedeem.coffeeId)
    Card(
        modifier = Modifier
            .fillMaxWidth(),
//            .clickable { onRedeemReward(coffeeRedeem) },
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
                painter = painterResource(coffeeRedeem.imageResource),
                contentDescription = coffeeRedeem.coffeeId.toString(),
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(coffee.name)
                Text("Valid until ${coffeeRedeem.validDate}", style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 2.dp),
                onClick = {  onRedeemReward(coffeeRedeem) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkBlue,
                    contentColor = Color.White
                ),
            ) {
                Text(
                    "${coffeeRedeem.redeemPoint} pts", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.White,
                    )
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RewardItemPreview() {
    RedeemRewardsScreen(
        navController = rememberNavController(),
        coffeeRedeemList = MockData.coffeeRedeemList
    ) { }
}
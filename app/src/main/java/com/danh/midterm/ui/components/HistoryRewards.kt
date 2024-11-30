package com.danh.midterm.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.model.RewardItem
import com.danh.midterm.ui.screens.RedeemItem
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DividerColor

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

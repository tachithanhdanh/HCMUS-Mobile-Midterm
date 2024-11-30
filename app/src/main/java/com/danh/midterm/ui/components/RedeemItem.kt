package com.danh.midterm.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.model.RewardItem
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DarkBlueLight

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
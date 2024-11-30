package com.danh.midterm.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.R
import com.danh.midterm.model.Order
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DividerColor

@Composable
fun OrderItem(
    order: Order,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !order.complete.value) {
                order.complete.value = true
            } // Disable click nếu `disabled = true`
            .graphicsLayer(alpha = if (order.complete.value) 0.5f else 1f), // Điều chỉnh độ mờ
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
                        text = order.getFormattedDate(), style = TextStyle(
                            color = DarkBlue,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500
                        )
                    )
                }
                Column {
                    Text(
                        text = "$${order.totalAmount}", style = TextStyle(
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
                    text = order.name, style = TextStyle(
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
                    text = order.address, style = TextStyle(
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
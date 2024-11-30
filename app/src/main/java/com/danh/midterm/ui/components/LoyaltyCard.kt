package com.danh.midterm.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danh.midterm.R
import com.danh.midterm.ui.theme.DarkBlue

@Composable
fun LoyaltyCard(
    currentStamps: Int,
    maxStamps: Int,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkBlue),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Để hàng chiếm toàn bộ chiều ngang
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp), // Khoảng cách dưới
                horizontalArrangement = Arrangement.SpaceBetween // Tạo khoảng cách đều giữa các phần tử
            ) {
                Text(
                    text = "Loyalty card",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
                Text(
                    text = "$currentStamps / $maxStamps",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                )
            }
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 16.dp) // Padding lớn hơn để tạo khoảng cách từ mép Card đến nội dung
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxSize(),
//                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween, // Căn chỉnh đều khoảng cách giữa các icon
                        verticalAlignment = Alignment.CenterVertically // Căn giữa icon theo chiều dọc
                    ) {
                        repeat(currentStamps) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_coffee_cup_1),
                                contentDescription = null,
                                tint = Color.Unspecified // Không áp dụng tint
                            )
                        }
                        repeat(maxStamps - currentStamps) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_coffee_cup_2),
                                contentDescription = null,
                                tint = Color.Unspecified // Không áp dụng tint
                            )
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoyatyCardPreview() {
    LoyaltyCard(currentStamps = 4, maxStamps = 8)
}

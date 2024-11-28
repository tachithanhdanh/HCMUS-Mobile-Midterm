package com.danh.midterm.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.danh.midterm.R
import com.danh.midterm.ui.theme.CoffeeItemCardColor
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.LightTextColor
import com.danh.midterm.ui.theme.TextColor

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.padding(30.dp).fillMaxHeight(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Profile",
                style = TextStyle.Default.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextColor
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }


        ProfileItem(
            icon = R.drawable.ic_profile,
            label = "Full name",
            value = "Anderson",
            onEditClick = { /* Handle edit click */ }
        )
        ProfileItem(
            icon = R.drawable.ic_phone,
            label = "Phone number",
            value = "+60134589525",
            onEditClick = { /* Handle edit click */ }
        )
        ProfileItem(
            icon = R.drawable.ic_message,
            label = "Email",
            value = "Anderson@email.com",
            onEditClick = { /* Handle edit click */ }
        )
        ProfileItem(
            icon = R.drawable.ic_location,
            label = "Phone number",
            value = "3 Addersion Court\n" +
                    "Chino Hills, HO56824, United State",
            onEditClick = { /* Handle edit click */ }
        )
    }
}

@Composable
fun ProfileItem(
    @DrawableRes icon: Int,
    label: String,
    value: String,
    onEditClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp) // Kích thước tổng thể của icon
                .background(
                    color = CoffeeItemCardColor, // Màu nền
                    shape = CircleShape // Bo góc tròn
                )
                .border(
                    width = 2.dp, // Độ dày viền
                    color = Color.Transparent, // Màu viền
                    shape = CircleShape // Bo góc viền tròn
                ),
            contentAlignment = Alignment.Center // Căn chỉnh icon vào giữa
        ) {
            Icon(
                painter = painterResource(id = icon), // Thay bằng tài nguyên icon của bạn
                contentDescription = label,
                tint = Color.Unspecified, // Màu của icon
                modifier = Modifier.size(20.dp) // Kích thước icon bên trong
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                style = TextStyle.Default.copy(
                    color = LightTextColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = value,
                style = TextStyle.Default.copy(
                    color = DarkBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600
                )
            )
        }
        IconButton(onClick = onEditClick) {
            Icon(painterResource(id = R.drawable.ic_edit), contentDescription = "Edit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
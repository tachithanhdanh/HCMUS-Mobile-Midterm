package com.danh.midterm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.GrayTextColor
import com.danh.midterm.ui.theme.OtherTextColor

@Composable
fun OrderSuccessScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painterResource(id = R.drawable.img_take_away),
            contentDescription = "Order Icon"
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Order Success",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W500,
                color = OtherTextColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your order has been placed successfully.\nFor more details, go to my orders.",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp,
                color = GrayTextColor
            )
        )
        Spacer(modifier = Modifier.height(80.dp))
        Button(
            modifier = Modifier
//                .padding(16.dp)
                .height(48.dp)
                .fillMaxWidth(),
            onClick = { /* Handle track order */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkBlue,
                contentColor = Color.White
            ),
        ) {
            Text(
                "Track my order",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W600)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSuccessScreenPreview() {
    OrderSuccessScreen()
}
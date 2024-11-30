package com.danh.midterm.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.ui.theme.CoffeeItemCardColor

@Composable
fun CoffeeCartItem(
    @DrawableRes coffeeImage: Int,
    coffeeName: String,
    options: String,
    totalAmount: Double,
    quantity: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp)
            ) // Add shadow for elevation
            .background(color = CoffeeItemCardColor)
            .clip(RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column { // Image
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                // Image
                Image(
                    painter = painterResource(id = coffeeImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
        // Coffee name and size
        Column {
            Text(
                text = coffeeName,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = options,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W300)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "x $quantity",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black.copy(alpha = 0.5f),
                    fontWeight = FontWeight.W600
                )
            )
        }

        // Quantity and price
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$%.2f".format(totalAmount),
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}
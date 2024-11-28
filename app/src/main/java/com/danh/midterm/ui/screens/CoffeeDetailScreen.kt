package com.danh.midterm.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.R
import com.danh.midterm.ui.theme.CoffeeItemCardColor
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DividerColor
import com.danh.midterm.ui.theme.Gray
import com.danh.midterm.ui.theme.TextColor

@Composable
fun CoffeeDetailScreen(
    coffeeName: String,
    imageResource: Int,
    price: Float,
    onAddToCart: () -> Unit
) {
    var selectedShot by remember { mutableStateOf("Single") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header: back button and cart icon
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle back */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Details",
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(onClick = { /* Handle cart click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_cart),
                    contentDescription = "Cart"
                )
            }
        }

        // Image of coffee
        Row(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(12.dp)
                ) // Add shadow for elevation
                .background(color = CoffeeItemCardColor)
                .clip(RoundedCornerShape(12.dp))
                .height(200.dp),
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = coffeeName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Coffee Name and Quantity
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = coffeeName,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(
                        width = 1.dp, // Độ dày của viền
                        color = Gray.copy(alpha = 0.4f), // Màu viền
                        shape = RoundedCornerShape(40.dp) // Bo góc
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp) // Thêm padding bên trong viền
                    .clip(RoundedCornerShape(40.dp)) // Đảm bảo viền và góc bo khớp nhau
            ) {
                IconButton(
                    onClick = { /* Decrease quantity */ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = "Decrease"
                    )
                }
                Text(text = "1", style = MaterialTheme.typography.bodyMedium)
                IconButton(
                    onClick = { /* Increase quantity */ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = "Increase"
                    )
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = DividerColor, // Màu nhạt
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Shot Type (Single/Double)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Shot", fontWeight = FontWeight.W500, fontSize = 14.sp, fontFamily = FontFamily.SansSerif)
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Button for "Single"
                Button(
                    onClick = { selectedShot = "Single" },
                    modifier = Modifier,
//                        .padding(4.dp)
//                        .width(120.dp), // Adjust the size if necessary
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedShot == "Single") DarkBlue else Color.White
                    ),
                    shape = RoundedCornerShape(50), // This creates the rounded button
                    border = BorderStroke(1.dp, if (selectedShot == "Single") DarkBlue else Gray)
                ) {
                    Text(
                        text = "Single",
                        color = if (selectedShot == "Single") Color.White else TextColor
                    )
                }
                // Spacer for horizontal spacing
                Spacer(modifier = Modifier.width(16.dp)) // Adjust the spacing as needed

                // Button for "Double"
                Button(
                    onClick = { selectedShot = "Double" },
                    modifier = Modifier,
//                        .padding(4.dp)
//                        .width(120.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedShot == "Double") DarkBlue else Color.White
                    ),
                    shape = RoundedCornerShape(50), // This creates the rounded button
                    border = BorderStroke(1.dp, if (selectedShot == "Double") DarkBlue else Gray)
                ) {
                    Text(
                        text = "Double",
                        color = if (selectedShot == "Double") Color.White else TextColor
                    )
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = DividerColor, // Màu nhạt
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Select
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Select", fontWeight = FontWeight.W500, fontSize = 14.sp, fontFamily = FontFamily.SansSerif)
            Row {
                IconButton(onClick = { /* Handle Size selection */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cup_coffee_unselected),
                        contentDescription = "Cup coffee",
                        tint = Color.Unspecified // Loại bỏ màu mặc định
                    )
                }
                IconButton(onClick = { /* Handle Size selection */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cup_ice_selected),
                        contentDescription = "Cup ice",
                        tint = Color.Unspecified // Loại bỏ màu mặc định
                    )
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = DividerColor, // Màu nhạt
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Size
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Size", fontWeight = FontWeight.W500, fontSize = 14.sp, fontFamily = FontFamily.SansSerif)
            Row {
                IconButton(onClick = { /* Handle Size selection */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cup_unselected),
                        contentDescription = "Small",
                        tint = Color.Unspecified, // Loại bỏ màu mặc định
                        modifier = Modifier.size(16.dp)
                    )
                }
                IconButton(onClick = { /* Handle Size selection */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cup_selected),
                        contentDescription = "Medium",
                        tint = Color.Unspecified, // Loại bỏ màu mặc định
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(onClick = { /* Handle Size selection */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cup_unselected),
                        contentDescription = "Large",
                        tint = Color.Unspecified, // Loại bỏ màu mặc định
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = DividerColor, // Màu nhạt
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Ice
        // https://stackoverflow.com/a/75025518
        Row(
            modifier = Modifier
                .fillMaxWidth(),
//                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Ice", fontWeight = FontWeight.W500, fontSize = 14.sp, fontFamily = FontFamily.SansSerif)
            Row {
                IconButton(onClick = { /* Handle Size selection */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_one_ice_unselected),
                        contentDescription = "One ice",
                        tint = Color.Unspecified // Loại bỏ màu mặc định
                    )
                }
                IconButton(onClick = { /* Handle Size selection */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_two_ice_unselected),
                        contentDescription = "Two ice",
                        tint = Color.Unspecified // Loại bỏ màu mặc định
                    )
                }
                IconButton(onClick = { /* Handle Size selection */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_three_ice_selected),
                        contentDescription = "Three Ice",
                        tint = Color.Unspecified // Loại bỏ màu mặc định
                    )
                }
            }
        }

        Row(
            modifier = Modifier.weight(1f),
        ) {

        }

        // Total Amount
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total Amount",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = TextColor
            )
            Text(
                text = "$$price",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = TextColor
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add to Cart Button
        Button(
            onClick = { onAddToCart() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkBlue,
                contentColor = Color.White
            ),
        ) {
            Text(text = "Add to Cart", fontWeight = FontWeight.W600, fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoffeeDetailScreenPreview() {
    CoffeeDetailScreen(
        coffeeName = "Americano",
        imageResource = R.drawable.img_americano,
        price = 3.00f,
        onAddToCart = {}
    )
}

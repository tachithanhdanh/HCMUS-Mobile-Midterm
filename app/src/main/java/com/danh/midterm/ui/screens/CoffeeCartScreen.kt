package com.danh.midterm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.R
import com.danh.midterm.model.getCoffeeDrawable
import com.danh.midterm.ui.theme.CoffeeItemCardColor
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.TextColor

@Composable
fun CoffeeCartScreen(
    cartItems: List<CartItem>,
    onCheckout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
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
        }
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
        ) {
            Text(
                text = "My Cart",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W500, color = TextColor)
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
//                .padding(horizontal = 24.dp)
        ) {
            items(cartItems) { item ->
                CoffeeCartItem(
                    coffeeName = item.coffeeName,
                    options = item.options,
                    size = item.size,
                    price = item.price,
                    quantity = item.quantity,
                    onQuantityChange = { /* Handle quantity change */ }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row {
                    Text(
                        text = "Total Price",
                        style = TextStyle(fontSize = 16.sp, color = TextColor.copy(alpha = 0.25f))
                    )
                }
                Row {
                    Text(
                        text = "$%.2f".format(cartItems.sumOf { it.price }),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W600, color = TextColor)
                    )
                }
            }

            Column {
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(48.dp),
                    onClick = onCheckout,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkBlue,
                        contentColor = Color.White
                    ),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_cart),
                        contentDescription = "Cart",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Checkout",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W600)
                    )
                }
            }
        }

    }
}

@Composable
fun CoffeeCartItem(
    coffeeName: Int,
    options: String,
    size: Int,
    price: Double,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp)
            ) // Add shadow for elevation
            .background(color = CoffeeItemCardColor)
            .clip(RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column { // Image
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                // Image
                Image(
                    painter = painterResource(id = getCoffeeDrawable(coffeeName)),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
        // Coffee name and size
        Column {
            Text(
                text = stringResource(coffeeName),
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = options,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W300)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "x $size",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black.copy(alpha = 0.5f),
                    fontWeight = FontWeight.W600
                )
            )
        }

        // Quantity and price
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
//            IconButton(
//                onClick = { onQuantityChange(quantity - 1) },
//                enabled = quantity > 1
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Decrease quantity"
//                )
//            }
//            Text(
//                text = quantity.toString(),
//                modifier = Modifier.padding(horizontal = 8.dp),
//                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
//            )
//            IconButton(
//                onClick = { onQuantityChange(quantity + 1) }
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Increase quantity"
//                )
//            }
//            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "$%.2f".format(price),
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

data class CartItem(
    val coffeeName: Int,
    val options: String,
    val size: Int,
    val price: Double,
    val quantity: Int = 1
)

@Preview(showBackground = true)
@Composable
fun CoffeeCartScreenPreview() {
    val cartItems = listOf(
        CartItem(R.string.americano, "single | iced | medium | full ice", 3, 1.0, 5),
        CartItem(R.string.cappuccino, "single | iced | medium | full ice", 3, 3.0, 7),
        CartItem(R.string.flat_white, "single | iced | medium | full ice", 3, 5.0, 9)
    )

    CoffeeCartScreen(
        cartItems = cartItems,
        onCheckout = { /* Handle checkout action */ }
    )
}


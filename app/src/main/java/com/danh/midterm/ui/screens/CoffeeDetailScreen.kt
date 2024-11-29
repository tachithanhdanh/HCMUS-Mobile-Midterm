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
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danh.midterm.R
import com.danh.midterm.model.CartItem
import com.danh.midterm.ui.theme.CoffeeItemCardColor
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DividerColor
import com.danh.midterm.ui.theme.Gray
import com.danh.midterm.ui.theme.TextColor
import com.danh.midterm.viewmodel.CartViewModel
import com.danh.midterm.viewmodel.CoffeeViewModel

@Composable
fun CoffeeDetailScreen(
    navController: NavHostController,
    coffeeViewModel: CoffeeViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel(),
    coffeeId: Int,
    onAddToCart: (CartItem) -> Unit,
    onBack: () -> Unit
) {
    val coffee = coffeeViewModel.findCoffeeById(coffeeId)
    var selectedShot by remember { mutableStateOf("single") }
    var quantity by remember { mutableStateOf(1) }
    var selectedType by remember { mutableStateOf("hot") }
    var selectedSize by remember { mutableStateOf(1) }
    var selectedIce by remember { mutableStateOf(1) }
    val icePrice = 0.5
    val sizePrice = 0.5
    val shotPrice = 0.5
    val totalAmount =
        quantity * (coffee.price + icePrice * selectedIce + shotPrice * (if (selectedShot == "double") 1 else 0) + sizePrice * (selectedSize - 1))

    fun getSize(): String {
        return when (selectedSize) {
            1 -> "small"
            2 -> "medium"
            else -> "large"
        }
    }

    fun getIce(): String {
        return when (selectedIce) {
            1 -> "little ice"
            2 -> "half ice"
            else -> "full ice"
        }
    }

    Scaffold(
    ) {
        // Scaffold is used to provide the top bar and bottom bar
            padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(vertical = 32.dp, horizontal = 24.dp)
        ) {
            // Header: back button and cart icon
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color.Unspecified // Loại bỏ màu mặc định
                    )
                }
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextColor
                )
                IconButton(onClick = { /* Handle cart click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_cart),
                        contentDescription = "Cart",
                        tint = Color.Unspecified // Loại bỏ màu mặc định
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
                    painter = painterResource(id = coffee.imageResource),
                    contentDescription = coffee.name,
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
                    text = coffee.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextColor,
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
                        onClick = { quantity = if (quantity > 1) quantity - 1 else 1 },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = "Decrease",
                            tint = Color.Unspecified // Loại bỏ màu mặc định
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${quantity}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextColor
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(
                        onClick = {
                            quantity = if (quantity < 20) quantity + 1 else 20
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "Increase",
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

            // Shot Type (single/double)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Shot",
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = TextColor
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Button for "single"
                    Button(
                        onClick = { selectedShot = "single" },
                        modifier = Modifier,
//                        .padding(4.dp)
//                        .width(120.dp), // Adjust the size if necessary
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedShot == "single") DarkBlue else Color.White
                        ),
                        shape = RoundedCornerShape(50), // This creates the rounded button
                        border = BorderStroke(
                            1.dp,
                            if (selectedShot == "single") DarkBlue else Gray
                        )
                    ) {
                        Text(
                            text = "single",
                            color = if (selectedShot == "single") Color.White else TextColor
                        )
                    }
                    // Spacer for horizontal spacing
                    Spacer(modifier = Modifier.width(16.dp)) // Adjust the spacing as needed

                    // Button for "double"
                    Button(
                        onClick = { selectedShot = "double" },
                        modifier = Modifier,
//                        .padding(4.dp)
//                        .width(120.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedShot == "double") DarkBlue else Color.White
                        ),
                        shape = RoundedCornerShape(50), // This creates the rounded button
                        border = BorderStroke(
                            1.dp,
                            if (selectedShot == "double") DarkBlue else Gray
                        )
                    ) {
                        Text(
                            text = "double",
                            color = if (selectedShot == "double") Color.White else TextColor
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
                Text(
                    text = "Select",
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = TextColor
                )
                Row {
                    IconButton(onClick = { selectedType = "hot" }) {
                        Icon(
                            painter = painterResource(
                                id =
                                if (selectedType.equals("hot")) R.drawable.ic_cup_coffee_selected
                                else R.drawable.ic_cup_coffee_unselected
                            ),
                            contentDescription = "Cup coffee",
                            tint = Color.Unspecified, // Loại bỏ màu mặc định
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    IconButton(onClick = { selectedType = "iced" }) {
                        Icon(
                            painter = painterResource(
                                id =
                                if (selectedType.equals("iced")) R.drawable.ic_cup_ice_selected
                                else R.drawable.ic_cup_ice_unselected
                            ),
                            contentDescription = "Cup ice",
                            tint = Color.Unspecified, // Loại bỏ màu mặc định
                            modifier = Modifier.size(32.dp)
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
                Text(
                    text = "Size",
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = TextColor
                )
                Row {
                    IconButton(onClick = { selectedSize = 1 }) {
                        Icon(
                            painter = painterResource(
                                id =
                                if (selectedSize == 1) R.drawable.ic_cup_selected
                                else R.drawable.ic_cup_unselected
                            ),
                            contentDescription = "Small",
                            tint = Color.Unspecified, // Loại bỏ màu mặc định
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { selectedSize = 2 }) {
                        Icon(
                            painter = painterResource(
                                id =
                                if (selectedSize == 2) R.drawable.ic_cup_selected
                                else R.drawable.ic_cup_unselected
                            ),
                            contentDescription = "Medium",
                            tint = Color.Unspecified, // Loại bỏ màu mặc định
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    IconButton(onClick = { selectedSize = 3 }) {
                        Icon(
                            painter = painterResource(
                                id =
                                if (selectedSize == 3) R.drawable.ic_cup_selected
                                else R.drawable.ic_cup_unselected
                            ),
                            contentDescription = "Large",
                            tint = Color.Unspecified, // Loại bỏ màu mặc định
                            modifier = Modifier.size(36.dp)
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
                Text(
                    text = "Ice",
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = TextColor
                )
                Row {
                    IconButton(onClick = { selectedIce = 1 }) {
                        Icon(
                            painter = painterResource(
                                id =
                                if (selectedIce == 1) R.drawable.ic_one_ice_selected
                                else R.drawable.ic_one_ice_unselected
                            ),
                            contentDescription = "One ice",
                            tint = Color.Unspecified, // Loại bỏ màu mặc định
//                            modifier = Modifier.size(32.dp)
                        )
                    }
                    IconButton(onClick = { selectedIce = 2 }) {
                        Icon(
                            painter = painterResource(
                                id =
                                if (selectedIce == 2) R.drawable.ic_two_ice_selected
                                else R.drawable.ic_two_ice_unselected
                            ),
                            contentDescription = "Two ice",
                            tint = Color.Unspecified, // Loại bỏ màu mặc định
//                            modifier = Modifier.size(32.dp)
                        )
                    }
                    IconButton(onClick = { selectedIce = 3 }) {
                        Icon(
                            painter = painterResource(
                                id =
                                if (selectedIce == 3) R.drawable.ic_three_ice_selected
                                else R.drawable.ic_three_ice_unselected
                            ),
                            contentDescription = "Three Ice",
                            tint = Color.Unspecified, // Loại bỏ màu mặc định
//                            modifier = Modifier.size(32.dp)
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
                    text = "$${totalAmount}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = TextColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Add to Cart Button
            Button(
                onClick = {
                    onAddToCart(
                        CartItem(
                            id = cartViewModel.getNewId(),
                            image = coffee.imageResource,
                            name = coffee.name,
                            shot = selectedShot,
                            select = selectedType,
                            size = getSize(),
                            ice = getIce(),
                            quantity = quantity,
                            totalAmount = totalAmount
                        )
                    )
                },
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

}

@Preview(showBackground = true)
@Composable
fun CoffeeDetailScreenPreview() {
    val navController = rememberNavController()
    CoffeeDetailScreen(
        navController = navController,
        coffeeId = 1,
        onAddToCart = {},
        onBack = {}
    )
}

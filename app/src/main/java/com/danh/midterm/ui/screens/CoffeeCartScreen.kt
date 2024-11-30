package com.danh.midterm.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.danh.midterm.R
import com.danh.midterm.mock.MockData
import com.danh.midterm.model.CartItem
import com.danh.midterm.ui.theme.CoffeeItemCardColor
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DeleteBackGroundColor
import com.danh.midterm.ui.theme.TextColor
import com.danh.midterm.viewmodel.CartViewModel
import kotlin.math.roundToInt

@Composable
fun CoffeeCartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = viewModel(),
    onCheckout: () -> Unit,
    onBack: () -> Unit = { navController.popBackStack() },
    onDelete: (Int) -> Unit
) {
    val cartItems = cartViewModel.cartItems
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
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
                            text = "$%.2f".format(cartItems.sumOf { it.totalAmount }),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                                color = TextColor
                            )
                        )
                    }
                }

                Column {
                    Button(
                        modifier = Modifier
//                            .padding(16.dp)
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
        },
        containerColor = Color.White
    ) {
        padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            // Header: back button and cart icon

            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
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
                    SwipeableCoffeeCartItem(
                        coffeeImage = item.image,
                        coffeeName = item.name,
                        options = "${item.shot} | ${item.select} | ${item.size} | ${item.ice}",
                        totalAmount = item.totalAmount,
                        quantity = item.quantity,
                        onDelete = { onDelete(item.id) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }


        }
    }

}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun SwipeableCoffeeCartItem(
    @DrawableRes coffeeImage: Int,
    coffeeName: String,
    options: String,
    totalAmount: Double,
    quantity: Int,
    onDelete: () -> Unit // Callback khi bấm nút xóa
) {
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val maxSwipeDistance = with(LocalDensity.current) { 80.dp.toPx() } // Khoảng cách vuốt tối đa
    val anchors = mapOf(0f to 0, -maxSwipeDistance to 1) // 0: ban đầu, 1: vuốt để xóa

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) }, // Ngưỡng vuốt
                orientation = Orientation.Horizontal
            )
    ) {
        // Kiểm tra nếu vuốt thành công (swipeableState.currentValue == 1)
        // Nút xóa phía sau
        Column(
            modifier = Modifier
                .clickable {
                    if (swipeableState.currentValue == 1) {
                        onDelete()
                    }
                } // Bấm nút xóa
                .fillMaxWidth(0.2f)
                .height(80.dp)
                .align(Alignment.CenterEnd)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(12.dp)
                ) // Add shadow for elevation
                .background(color = DeleteBackGroundColor)
                .clip(RoundedCornerShape(12.dp))
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "Delete",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        // Nội dung chính (CoffeeCartItem)
        CoffeeCartItem(
            coffeeImage = coffeeImage,
            coffeeName = coffeeName,
            options = options,
            totalAmount = totalAmount,
            quantity = quantity,
            modifier = Modifier
                .offset {
                    IntOffset(
                        swipeableState.offset.value.roundToInt(),
                        0
                    )
                } // Dịch chuyển theo vuốt
        )
    }
}


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

@Preview(showBackground = true)
@Composable
fun CoffeeCartScreenPreview() {
    val cartItems = MockData.cartItems
    val navController = rememberNavController()
    CoffeeCartScreen(
        navController = navController,
        cartViewModel = CartViewModel(),
        onCheckout = { /* Handle checkout action */ },
        onDelete = { /* Handle delete action */ }
    )
}


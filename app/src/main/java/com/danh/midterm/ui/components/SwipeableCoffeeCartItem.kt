package com.danh.midterm.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.danh.midterm.R
import com.danh.midterm.ui.theme.DeleteBackGroundColor
import kotlin.math.roundToInt

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
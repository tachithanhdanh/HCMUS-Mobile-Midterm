package com.danh.midterm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.R
import com.danh.midterm.ui.theme.CoffeeItemCardColor
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.Gray
import com.example.ordercoffee.ui.components.BottomNavigationBar

@Composable
fun CoffeeOrderingUI(
    coffeeOptions: List<CoffeeOption>,
    loyaltyCardProgress: Int,
    onCoffeeSelected: (CoffeeOption) -> Unit
) {
    Card(
        modifier = Modifier
//            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
//            .background(color = DarkBlue),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp), // Bo góc trên
        colors = CardDefaults.cardColors(containerColor = DarkBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Thay đổi độ cao bóng,
    ) {
        // Wrap toàn bộ nội dung trong Column để áp dụng padding chung
        Column(
            modifier = Modifier
                .fillMaxWidth() // Padding cho toàn bộ nội dung bên trong
                .padding(16.dp)
        ) {
            Text(
                text = "Choose your coffee",
                style = TextStyle(
                    color = Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                ),
                modifier = Modifier.padding(bottom = 0.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(coffeeOptions) { option ->
                    CoffeeOptionCard(
                        option = option,
                        onClick = { onCoffeeSelected(option) }
                    )
                }
            }
            BottomNavigationBar()
        }
    }
}

@Composable
private fun CoffeeOptionsGrid(
    coffeeOptions: List<CoffeeOption>,
    onCoffeeSelected: (CoffeeOption) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(coffeeOptions) { option ->
            CoffeeOptionCard(
                option = option,
                onClick = { onCoffeeSelected(option) }
            )
        }
    }
}

@Composable
private fun CoffeeOptionCard(
    option: CoffeeOption,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
//            .background(color = White90),
        colors = CardDefaults.cardColors(containerColor = CoffeeItemCardColor),
//        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = option.imageResource),
                contentDescription = option.name,
                modifier = Modifier.size(96.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = option.name,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500
                )
            )
        }
    }
}

data class CoffeeOption(
    val name: String,
    val imageResource: Int
)

@Preview(showBackground = true)
@Composable
fun CoffeeOrderingUIPreview() {
    CoffeeOrderingUI(
        coffeeOptions = listOf(
            CoffeeOption("Americano", R.drawable.img_americano),
            CoffeeOption("Cappuccino", R.drawable.img_cappuccino),
            CoffeeOption("Mocha", R.drawable.img_mocha),
            CoffeeOption("Flat White", R.drawable.img_flat_white)
        ),
        loyaltyCardProgress = 4,
        onCoffeeSelected = {}
    )
}
package com.danh.midterm.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.ui.theme.DarkBlue
import com.danh.midterm.ui.theme.DarkBlueLight

@Composable
fun PointCard(
    currentPoint: Int,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkBlue),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(

            ) {

                Text(
                    text = "My Points:",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$currentPoint",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.W500,
                        fontSize = 24.sp
                    )
                )
            }
            Column {
                Button(
                    onClick = { onClick() },
                    modifier = Modifier,
//                        .padding(4.dp)
//                        .width(120.dp), // Adjust the size if necessary
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkBlueLight,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20), // This creates the rounded button
                ) {
                    Text(
                        text = "Redeem drink",
                        color = Color.White
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PointCardPreview() {
    PointCard(
        currentPoint = 2750
    )
}

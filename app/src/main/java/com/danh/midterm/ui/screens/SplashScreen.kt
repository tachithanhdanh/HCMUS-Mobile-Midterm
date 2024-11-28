package com.danh.midterm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danh.midterm.R
import com.danh.midterm.ui.theme.LogoTextColor

@Composable
fun SplashScreen(
    backgroundImageResource: Int,
    logoImageResource: Int,
    backgroundColor: Color = Color.Transparent
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            painter = painterResource(backgroundImageResource),
            contentDescription = "Splash Screen Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(logoImageResource),
                contentDescription = "Ordinary Coffee House Logo",
                modifier = Modifier
                    .size(128.dp)
            )
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = "Ordinary Coffee House",
                color = Color.White,
                style = TextStyle(
                    color = LogoTextColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W500
                ),
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        backgroundImageResource = R.drawable.background,
        logoImageResource = R.drawable.logo
    )
}
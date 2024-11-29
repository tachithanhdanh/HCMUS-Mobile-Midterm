package com.danh.midterm.model

import androidx.annotation.DrawableRes

data class Coffee(
    val id: Int,
    val name: String,
    val price: Double,
    @DrawableRes val imageResource: Int,
)
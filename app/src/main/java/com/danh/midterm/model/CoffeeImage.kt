package com.danh.midterm.model

import android.provider.Settings.Global.getString
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.danh.midterm.R

enum class CoffeeImage(val resourceId: Int) {
    AMERICANO(R.drawable.img_americano),
    CAPPUCCINO(R.drawable.img_cappuccino),
    MOCHA(R.drawable.img_mocha),
    FLAT_WHITE(R.drawable.img_flat_white)
}

fun getCoffeeDrawable(coffeeName: Int): Int {
    return when (coffeeName) {
        R.string.americano -> CoffeeImage.AMERICANO.resourceId
        R.string.cappuccino -> CoffeeImage.CAPPUCCINO.resourceId
        R.string.mocha -> CoffeeImage.MOCHA.resourceId
        R.string.flat_white -> CoffeeImage.FLAT_WHITE.resourceId
        else -> CoffeeImage.AMERICANO.resourceId
    }
}
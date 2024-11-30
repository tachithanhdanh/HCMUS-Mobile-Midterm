package com.danh.midterm.model

data class RewardPoint(
    val id: Int,
    val point: Int,
    val redeemList: List<CoffeeRedeem>
)
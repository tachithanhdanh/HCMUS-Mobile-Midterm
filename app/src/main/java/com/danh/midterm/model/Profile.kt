package com.danh.midterm.model

data class Profile(
    var name: String,
    var phoneNumber: String,
    var email: String,
    var address: String,
    var points: Int = 0,
    var stamps: Int = 0
)
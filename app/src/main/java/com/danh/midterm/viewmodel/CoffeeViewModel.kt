package com.danh.midterm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.danh.midterm.mock.MockData
import com.danh.midterm.model.Coffee

class CoffeeViewModel : ViewModel() {
    var coffeeList by mutableStateOf<List<Coffee>>(MockData.coffeeList)
        private set

    fun findCoffeeById(id: Int): Coffee {
        return coffeeList.find { it.id == id } ?: MockData.coffeeList[0]
    }

    fun addCoffee(coffee: Coffee) {
        coffeeList = coffeeList + coffee
    }

    fun removeCoffeeById(id: Int) {
        coffeeList = coffeeList.filter { it.id != id }
    }
}
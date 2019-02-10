package com.example.planner

data class CashFlow(
    val name: String,
    var frequency: Frequency,
    var amount: Double
)
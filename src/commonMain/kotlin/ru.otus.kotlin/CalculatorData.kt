package ru.otus.kotlin

import kotlinx.serialization.Serializable

enum class CalcAction(val code: String) {
    ADD("+"), MINUS("-"), DIV("/")
}

@Serializable
data class CalculatorData(
    val id: Int,

    val first: Double,
    val second: Double,
    val result: Double,

    val action: CalcAction,
)

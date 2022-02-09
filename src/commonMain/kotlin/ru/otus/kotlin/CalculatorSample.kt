package ru.otus.kotlin

import kotlinx.serialization.Serializable

enum class CalcAction(val code: String) {
    ADD("+"), MINUS("-"), DIV("/")
}

@Serializable
data class CalculatorSample(
    val id: Int,

    val first: Int,
    val second: Int,
    val result: Int,
    val action: CalcAction,
)

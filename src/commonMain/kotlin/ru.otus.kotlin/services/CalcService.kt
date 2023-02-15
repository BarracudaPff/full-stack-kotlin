package ru.otus.kotlin.services

import ru.otus.kotlin.CalculatorData

expect class CalcService {
    suspend fun addCalc(sample: CalculatorData)

    suspend fun getAll(): List<CalculatorData>
}

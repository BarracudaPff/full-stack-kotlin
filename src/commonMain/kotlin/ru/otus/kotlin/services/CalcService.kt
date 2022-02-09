package ru.otus.kotlin.services

import ru.otus.kotlin.CalculatorSample

expect class CalcService {
    actual suspend fun addCalc(sample: CalculatorSample)

    actual suspend fun getAllCalcs(): List<CalculatorSample>
}

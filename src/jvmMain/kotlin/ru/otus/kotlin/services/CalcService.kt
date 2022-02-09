package ru.otus.kotlin.services

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import ru.otus.kotlin.CalculatorSample
import ru.otus.kotlin.database
import ru.otus.kotlin.database.CalcSamples

actual class CalcService {
    actual suspend fun addCalc(sample: CalculatorSample) {
        return database {
            CalcSamples.insert {
                it[calcId] = sample.id
                it[first] = sample.first
                it[second] = sample.second
                it[result] = sample.result
                it[action] = sample.action
            }
        }
    }

    actual suspend fun getAllCalcs(): List<CalculatorSample> {
        return database {
            CalcSamples.selectAll().map {
                CalculatorSample(
                    it[CalcSamples.calcId],
                    it[CalcSamples.first],
                    it[CalcSamples.second],
                    it[CalcSamples.result],
                    it[CalcSamples.action],
                )
            }.shuffled().take(10)
        }
    }

}

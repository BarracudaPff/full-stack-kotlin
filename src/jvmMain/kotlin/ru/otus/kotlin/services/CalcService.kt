package ru.otus.kotlin.services

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import ru.otus.kotlin.CalculatorData
import ru.otus.kotlin.database.CalcSample
import ru.otus.kotlin.database.database

actual class CalcService {
    actual suspend fun addCalc(sample: CalculatorData) {
        return database {
            CalcSample.insert {
                it[calcId] = sample.id
                it[first] = sample.first
                it[second] = sample.second
                it[result] = sample.result
                it[action] = sample.action
            }
        }
    }

    actual suspend fun getAll(): List<CalculatorData> {
        return database {
            CalcSample.selectAll().map {
                CalculatorData(
                    it[CalcSample.calcId],
                    it[CalcSample.first],
                    it[CalcSample.second],
                    it[CalcSample.result],
                    it[CalcSample.action],
                )
            }
        }
    }
}
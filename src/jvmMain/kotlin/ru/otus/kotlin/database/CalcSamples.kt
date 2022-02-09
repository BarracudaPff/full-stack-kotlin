package ru.otus.kotlin.database

import org.jetbrains.exposed.dao.id.IntIdTable
import ru.otus.kotlin.CalcAction

object CalcSamples : IntIdTable() {
    val calcId = integer("calc_id")

    val first = integer("first")
    val second = integer("second")
    val result = integer("result")
    val action = enumeration("action", CalcAction::class)
}

package ru.otus.kotlin.database

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction
import ru.otus.kotlin.CalcAction

object CalcSample : IntIdTable() {
    val calcId = integer("calc_id")

    val first = double("first")
    val second = double("second")
    val result = double("result")
    val action = enumeration("action", CalcAction::class)
}

fun <T> database(statement: Transaction.() -> T): T {
    return transaction {
        statement()
    }
}

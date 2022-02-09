package ru.otus.kotlin

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction


fun <T> database(statement: Transaction.() -> T): T {
    Database.connect("jdbc:h2:my-h2:8082./data/db;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

    return transaction {
        statement()
    }
}

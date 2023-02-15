package ru.otus.kotlin

actual fun log(message: String, level: LogLevel) {
    println("$level: $message")
}

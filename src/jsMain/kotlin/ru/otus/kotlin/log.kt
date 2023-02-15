package ru.otus.kotlin

actual fun log(message: String, level: LogLevel) {
    console.log("$level: $message")
}
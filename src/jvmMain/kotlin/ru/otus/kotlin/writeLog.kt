package ru.otus.kotlin

internal actual fun writeLog(message: String, logLevel: LogLevel) {
    println("$message:$logLevel")
}

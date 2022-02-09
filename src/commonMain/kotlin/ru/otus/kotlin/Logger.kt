package ru.otus.kotlin

enum class LogLevel {
    WARN, ERROR, INFO
}

internal expect fun writeLog(message: String, logLevel: LogLevel)

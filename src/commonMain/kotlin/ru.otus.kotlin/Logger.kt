package ru.otus.kotlin

enum class LogLevel {
    WARN, ERROR, INFO
}

expect fun log(message: String, level: LogLevel)

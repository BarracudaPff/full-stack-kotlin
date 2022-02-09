package ru.otus.kotlin

internal actual fun writeLog(message: String, logLevel: LogLevel) {
    when (logLevel) {
        LogLevel.WARN -> console.warn(message)
        LogLevel.ERROR -> console.error(message)
        LogLevel.INFO -> console.info(message)
    }
}

package ru.otus.kotlin.services

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.fetch.RequestInit
import ru.otus.kotlin.CalculatorData
import kotlin.coroutines.coroutineContext
import kotlin.js.json

actual class CalcService {
    actual suspend fun addCalc(sample: CalculatorData) {
        fetch("/api/calc", "POST", Json.encodeToString(sample))
        return
    }

    actual suspend fun getAll(): List<CalculatorData> {
        return parse(
            ListSerializer(CalculatorData.serializer()),
            fetch("/api/calc", "POST")
        )
    }

    private fun <T> parse(strategy: DeserializationStrategy<T>, data: String): T {
        return Json.decodeFromString(strategy, data)
    }

    private suspend fun fetch(
        endpoint: String,
        method: String,
        body: String? = undefined
    ): String {
        return withContext(coroutineContext) {
            val response = window.fetch(
                endpoint, RequestInit(
                    method = method,
                    body = body,
                    headers = json(
                        "Accept" to "application/json",
                        "Content-Type" to "application/json"
                    )
                )
            ).await()

            response.text().await()
        }
    }
}
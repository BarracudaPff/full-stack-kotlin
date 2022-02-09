package ru.otus.kotlin.services

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.serializer
import org.w3c.fetch.RequestInit
import ru.otus.kotlin.CalculatorSample
import kotlin.coroutines.coroutineContext
import kotlin.js.json

actual class CalcService {
    actual suspend fun addCalc(sample: CalculatorSample) {
        fetch("/api/calc", "POST", Json.encodeToString(sample))
        return
    }

    actual suspend fun getAllCalcs(): List<CalculatorSample> {
        return parse(ListSerializer(CalculatorSample.serializer()), fetch("/api/calc", "GET"))
    }

    private fun <T> parse(serStrategy: DeserializationStrategy<T>, string: String): T {
        return Json.decodeFromString(serStrategy, string)
    }

    suspend fun fetch(url: String, method: String, body: String? = undefined): String {
        return withContext(coroutineContext) {
            val response = window.fetch(
                url, RequestInit(
                    method = method, body = body, headers = json(
                        "Accept" to "application/json",
                        "Content-Type" to "application/json"
                    )
                )
            ).await()

            response.text().await()
        }
    }
}

package ru.otus.kotlin

import react.create
import react.dom.client.createRoot
import ru.otus.kotlin.services.CalcService

fun main() {
    web.window.window.onload = {
        val container = web.dom.document.getElementById("root")!!

        val myCalcService = CalcService()

        createRoot(container).render(
            CalculatorComponent.create {
                first = "12"
                second = "2"
                calcService = myCalcService
            }
//            Welcome.create {
//                name = "Kotlin'er"
//            }
        )
    }
}

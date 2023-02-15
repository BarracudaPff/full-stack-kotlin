package ru.otus.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.useState
import ru.otus.kotlin.services.CalcService
import web.html.InputType
import kotlin.random.Random

external interface CalculatorProps : Props {
    var first: String
    var second: String
    var calcService: CalcService
}

val CalculatorComponent = FC<CalculatorProps> {
    var first by useState(it.first)
    var second by useState(it.second)
    var action by useState(CalcAction.MINUS)
    var result by useState<String?>(undefined)

    val calcService = it.calcService

    h1 {
        +"Calculator sample"
    }

    input {
        type = InputType.number
        value = first
        onChange = { e ->
            first = e.target.value
        }
    }
    input {
        type = InputType.number
        value = second
        onChange = { e ->
            second = e.target.value
        }
    }

    select {
        option {
            value = CalcAction.ADD
            +CalcAction.ADD.code
        }
        option {
            value = CalcAction.MINUS
            +CalcAction.MINUS.code
        }
        option {
            value = CalcAction.DIV
            +CalcAction.DIV.code
        }
        onChange = { e ->
            action = CalcAction.valueOf(e.target.value)
        }
    }

    div {
       button {
           +"Compute"
           onClick = {
               GlobalScope.launch {
                   val firstValue = first.toDoubleOrNull() ?: 0.0
                   val secondValue = second.toDoubleOrNull() ?: 0.0

                   val res = when (action) {
                       CalcAction.ADD -> firstValue + secondValue
                       CalcAction.MINUS -> firstValue - secondValue
                       CalcAction.DIV -> firstValue / secondValue
                   }

                   calcService.addCalc(
                       CalculatorData(
                           Random.nextInt(),
                           firstValue,
                           secondValue,
                           res,
                           action
                       )
                   )

                   result = res.toString()
               }
           }
       }
    }

    if (result != null) {
        div {
            +result
        }
    }
}
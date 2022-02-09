package ru.otus.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.*
import react.dom.html.ReactHTML.h1
import ru.otus.kotlin.services.CalcService
import styled.css
import styled.styledDiv
import styled.styledInput
import kotlin.random.Random

external interface CalculatorProps : Props {
    var first: String
    var second: String
}

data class CalculatorState(
    val first: String,
    val second: String,
    val action: CalcAction,
    val result: String? = null,
) : State

class CalculatorComponent(props: CalculatorProps) : RComponent<CalculatorProps, CalculatorState>(props) {
    val calcService = CalcService()

    init {
        state = CalculatorState(props.first, props.second, CalcAction.ADD)
    }

    override fun RBuilder.render() {
        h1 {
            +"Calculator sample"
        }
        styledInput {
            css {
                +ClientStyles.textInput
            }
            attrs {
                type = InputType.number
                value = state.first
                onChangeFunction = { event ->
                    setState(
                        CalculatorState(
                            (event.target as HTMLInputElement).value,
                            state.second,
                            state.action,
                            state.result,
                        )
                    )
                }
            }
        }
        styledInput {
            css {
                +ClientStyles.textInput
            }
            attrs {
                type = InputType.number
                value = state.second
                onChangeFunction = { event ->
                    setState(
                        CalculatorState(
                            state.first,
                            (event.target as HTMLInputElement).value,
                            state.action,
                            state.result,
                        )
                    )
                }
            }
        }

        select {
            option {
                attrs {
                    attrs.value = CalcAction.ADD.code
                    +CalcAction.ADD.code
                }
            }
            option {
                attrs {
                    attrs.value = CalcAction.MINUS.code
                    +CalcAction.MINUS.code
                }
            }
            option {
                attrs {
                    attrs.value = CalcAction.DIV.code
                    +CalcAction.DIV.code
                }
            }
        }

        styledDiv {
            css {
                +ClientStyles.textContainer
            }
            button {
                +"Вычислить"
                attrs {
                    onClickFunction = {
                        val res = "" + (state.first.toInt() + state.second.toInt())


                        GlobalScope.launch {
                            calcService.addCalc(
                                CalculatorSample(
                                    Random.Default.nextInt(),
                                    state.first.toInt(),
                                    state.second.toInt(),
                                    res.toInt(),
                                    state.action,
                                )
                            )
                        }

                        setState(
                            CalculatorState(
                                state.first,
                                state.second,
                                state.action,
                                res,
                            )
                        )
                    }
                }
            }
        }

        if (state.result != null) {
            div {
                +state.result!!
            }
        }
    }
}

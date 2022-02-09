package ru.otus.kotlin

import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("root")!!) {
            child(CalculatorComponent::class) {
                attrs {
                    first = "0"
                    second = "0"
                }
            }
        }
    }
}

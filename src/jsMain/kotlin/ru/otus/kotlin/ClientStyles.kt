package ru.otus.kotlin

import kotlinx.css.*
import styled.StyleSheet

object ClientStyles : StyleSheet("CalculatorStyles", isStatic = true) {
    val textInput by css {
        margin(vertical = 5.px)
        width = 40.px
        fontSize = 14.px
    }

    val textContainer by css {
        padding(5.px)
    }
}

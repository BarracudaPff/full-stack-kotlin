package ru.otus.kotlin

import react.create
import react.dom.client.createRoot

fun main() {
    web.window.window.onload = {
        console.log("!#")
        val container = web.dom.document.getElementById("root")!!

        createRoot(container).render(
            Welcome.create {
                name = "Kotlin'er"
            }
        )
    }
}

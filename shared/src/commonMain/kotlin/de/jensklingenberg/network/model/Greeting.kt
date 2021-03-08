package de.jensklingenberg.network.model


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}

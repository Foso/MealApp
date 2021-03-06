package de.jensklingenberg.newmyapplication.shared.models

import de.jensklingenberg.newmyapplication.shared.Platform


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}

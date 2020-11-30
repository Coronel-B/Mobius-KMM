package app.mobius.util

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
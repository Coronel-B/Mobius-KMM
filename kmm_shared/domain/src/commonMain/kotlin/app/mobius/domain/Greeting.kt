package app.mobius.domain

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
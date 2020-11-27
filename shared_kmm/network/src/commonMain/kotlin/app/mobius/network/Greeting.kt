package app.mobius.network

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
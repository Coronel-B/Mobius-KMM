package app.mobius.data_access

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
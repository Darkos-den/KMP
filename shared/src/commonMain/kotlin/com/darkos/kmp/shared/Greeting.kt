package com.darkos.kmp.shared

@Deprecated("old api")
class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}

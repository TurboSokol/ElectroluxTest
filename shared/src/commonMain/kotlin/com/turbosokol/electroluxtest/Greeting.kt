package com.turbosokol.electroluxtest

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
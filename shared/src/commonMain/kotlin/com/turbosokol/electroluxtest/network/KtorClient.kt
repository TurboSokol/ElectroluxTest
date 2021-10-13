package com.turbosokol.electroluxtest.network

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

fun ktorClient() = HttpClient(HttpEngineFactory().createEngine()) {
    install(feature = JsonFeature) {
        serializer = KotlinxSerializer()

    }
    install(feature = Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.BODY
    }
}
package com.turbosokol.electroluxtest.di

import com.turbosokol.electroluxtest.network.HttpEngineFactory
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun createKoin() = startKoin{
    modules()
}

fun commonModule() = module {
    single { CoroutineScope(Dispatchers.Default + Job()) }
    single { createKtorClient() }
    //TODO::init repository
}

fun createKtorClient() = HttpClient(HttpEngineFactory().createEngine()) {
    install(feature = JsonFeature) {
        serializer = KotlinxSerializer()

    }
    install(feature = Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.BODY
    }
}
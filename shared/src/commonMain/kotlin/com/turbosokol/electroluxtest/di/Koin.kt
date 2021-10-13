package com.turbosokol.electroluxtest.di

import com.turbosokol.electroluxtest.data.FlickrRepository
import com.turbosokol.electroluxtest.data.FlickrRepositoryInterface
import com.turbosokol.electroluxtest.network.FlickrApi
import com.turbosokol.electroluxtest.network.HttpEngineFactory
import com.turbosokol.electroluxtest.network.ktorClient
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule())
}

fun commonModule() = module {
    single { CoroutineScope(Dispatchers.Default + Job()) }
    single { ktorClient() }
    single<FlickrRepositoryInterface> { FlickrRepository() }
    single { FlickrApi(get()) }
}


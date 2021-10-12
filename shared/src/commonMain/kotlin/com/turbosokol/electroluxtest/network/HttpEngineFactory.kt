package com.turbosokol.electroluxtest.network

import io.ktor.client.engine.*

//HTTP client factory for use ktor in different platforms
expect class HttpEngineFactory constructor() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}
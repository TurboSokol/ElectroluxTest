package com.turbosokol.electroluxtest.network

import io.ktor.client.engine.*

expect class HttpEngineFactory constructor() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}
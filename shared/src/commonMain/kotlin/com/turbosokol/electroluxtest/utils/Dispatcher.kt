package com.turbosokol.electroluxtest.utils

import kotlinx.coroutines.CoroutineDispatcher

//Dispatcher for use asynchronous ktor network
//queries in different platforms
expect val expectedDispatcher: CoroutineDispatcher
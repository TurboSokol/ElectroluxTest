package com.turbosokol.electroluxtest.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

val applicationScope = CoroutineScope(expectedDispatcher + Job())
package com.turbosokol.electroluxtest.android.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

//UI scope for bitmap operations
val localIoScope = CoroutineScope(Dispatchers.IO + Job())
//Main scope for callbacks from IU scope
val localMainScope = CoroutineScope(Dispatchers.Main + Job())
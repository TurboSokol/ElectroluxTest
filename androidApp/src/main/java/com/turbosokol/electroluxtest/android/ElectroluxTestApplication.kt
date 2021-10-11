package com.turbosokol.electroluxtest.android

import android.app.Application
import com.turbosokol.electroluxtest.android.di.appModule
import com.turbosokol.electroluxtest.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent

class ElectroluxTestApplication: Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        initKoin() {
            androidContext(this@ElectroluxTestApplication)
            modules(appModule)
        }

    }

}
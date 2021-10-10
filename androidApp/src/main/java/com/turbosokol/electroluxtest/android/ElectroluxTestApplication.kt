package com.turbosokol.electroluxtest.android

import android.app.Application
import com.turbosokol.electroluxtest.android.di.appModule
import com.turbosokol.electroluxtest.di.createKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

class ElectroluxTestApplication: Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        createKoin() {
            androidContext(this@ElectroluxTestApplication)
            modules(appModule)
        }

    }

}
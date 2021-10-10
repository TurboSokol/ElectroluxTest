package com.turbosokol.electroluxtest.android.di

import com.turbosokol.electroluxtest.android.viewmodels.FlickrViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { FlickrViewModel(get()) }
}
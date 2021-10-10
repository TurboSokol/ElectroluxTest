package com.turbosokol.electroluxtest.android.viewmodels

import androidx.lifecycle.ViewModel
import com.turbosokol.electroluxtest.data.FlickrRepository
import org.koin.core.component.KoinComponent

class FlickrViewModel(
    private val repository: FlickrRepository
): ViewModel() {
}
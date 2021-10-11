package com.turbosokol.electroluxtest.android.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turbosokol.electroluxtest.data.FlickrRepository
import com.turbosokol.electroluxtest.data.FlickrResponseModel
import com.turbosokol.electroluxtest.data.PhotoItem
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class FlickrViewModel(
    private val repository: FlickrRepository
): ViewModel() {
    val imageList: MutableState<List<PhotoItem?>> = mutableStateOf(listOf())

    //Request on app starting
    fun fetchElectroluxImages() {
        viewModelScope.launch {
            repository.fetchElectroluxImages() { response ->
                imageList.value = response.photos.let { response.photos!!.photo!! }
            }
        }
    }

    //TODO:: DYNAMIC REQUEST

}
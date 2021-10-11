package com.turbosokol.electroluxtest.android.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turbosokol.electroluxtest.data.FlickrRepositoryInterface
import com.turbosokol.electroluxtest.data.PhotoItem
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class FlickrViewModel(): ViewModel(), KoinComponent {
    private val repository: FlickrRepositoryInterface by inject()
    val imageList: MutableState<List<PhotoItem?>> = mutableStateOf(listOf())
    val searchTag: MutableState<String> = mutableStateOf("")

    //Request on app starting
    fun fetchElectroluxImages() {
        viewModelScope.launch {
            repository.fetchElectroluxImages() { response ->
                imageList.value = response.photos.let { response.photos!!.photo!! }
            }
        }
    }

    fun onSearchTagChanged(newValue: String) {
        searchTag.value = newValue
    }

   fun fetchSearchedImages(searchTag: String) {
       viewModelScope.launch {
           repository.fetchSearchedImages(searchTag) { response ->
               //TODO:: CORRECT CHECS
               imageList.value = response.photos.let { response.photos!!.photo!! }
           }
       }
   }

}
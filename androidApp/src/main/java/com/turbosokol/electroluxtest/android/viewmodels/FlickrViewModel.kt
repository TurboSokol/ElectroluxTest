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


class FlickrViewModel(private val repository: FlickrRepositoryInterface) : ViewModel(),
    KoinComponent {
    val imageList: MutableState<List<PhotoItem?>> = mutableStateOf(listOf())
    val searchTag: MutableState<String> = mutableStateOf("")
    val photoUrl: MutableState<String> = mutableStateOf("")

    //Request on app starting
    fun fetchElectroluxImages() {
        viewModelScope.launch {
            repository.fetchElectroluxImages() { response ->
                imageList.value =
                    response.photos.let { response.photos!!.photo.let { response.photos!!.photo!! } }
            }
        }
    }

    //Dynamic request from search bar
    fun fetchSearchedImages(searchTag: String) {
        viewModelScope.launch {
            repository.fetchSearchedImages(searchTag) { response ->
                imageList.value =
                    response.photos.let { response.photos!!.photo.let { response.photos!!.photo!! } }
            }
        }
    }

    //Set observable value for search
    fun onSearchTagChanged(newValue: String) {
        searchTag.value = newValue
    }

    //Set observable value for image in Detail screen
    fun setPhotoUrl(newValue: String) {
        photoUrl.value = newValue
    }
}
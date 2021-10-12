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
                if (response.photos != null) {
                    imageList.value =
                        response.photos!!.photo.let { response.photos!!.photo!! }
                } else {
                    imageList.value = listOf(
                        PhotoItem(
                            owner = null,
                            server = null,
                            isfriend = null,
                            ispublic = null,
                            farm = null,
                            id = null,
                            secret = null,
                            url_m = null,
                            title = null,
                            height_m = null,
                            width_m = null,
                            isfamily = null
                        )
                    )
                }
            }
        }
    }

    //Dynamic request from search bar
    fun fetchSearchedImages(searchTag: String) {
        viewModelScope.launch {
            repository.fetchSearchedImages(searchTag) { response ->
                if (response.photos != null) {
                    imageList.value =
                        response.photos!!.photo.let { response.photos!!.photo!! }
                } else {
                    fetchElectroluxImages()
                }
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

    fun clearImageList() {
        imageList.value = listOf()
    }
}
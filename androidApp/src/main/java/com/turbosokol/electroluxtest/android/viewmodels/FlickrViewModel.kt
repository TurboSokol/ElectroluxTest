package com.turbosokol.electroluxtest.android.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turbosokol.electroluxtest.data.FlickrRepositoryInterface
import com.turbosokol.electroluxtest.data.PhotoItem
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


class FlickrViewModel(private val repository: FlickrRepositoryInterface) : ViewModel(),
    KoinComponent {
    //List of fetched images
    val imageList: MutableState<List<PhotoItem?>> = mutableStateOf(listOf())
    //Mutable state for reading and displaying user search requests
    val searchTag: MutableState<String> = mutableStateOf("")
    //Mutable state flag to show different looks of selected items in lazy column
    val onSelected: MutableState<Boolean> = mutableStateOf(false)
    //Mutable state for changing UI in a target lazy column items
    val itemIndex: MutableState<Int> = mutableStateOf(0)

    //Request on app starting
    fun fetchElectroluxImages() {
        viewModelScope.launch {
            repository.fetchElectroluxImages() { response ->
                if (response.photos != null) {
                    imageList.value =
                        response.photos!!.photo.let { it!! }
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
                        response.photos!!.photo.let { it!! }
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

    //Clearing list for showing load indicator
    fun clearImageList() {
        imageList.value = listOf()
    }

    fun switchOnSelected(newValue: Boolean) {
        onSelected.value = newValue
    }

    fun setIndex(newValue: Int) {
        itemIndex.value = newValue
    }
}
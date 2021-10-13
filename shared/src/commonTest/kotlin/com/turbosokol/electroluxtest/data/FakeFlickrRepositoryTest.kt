package com.turbosokol.electroluxtest.data

import io.ktor.client.features.logging.*

class FakeFlickrRepositoryTest : FlickrRepositoryInterface {

    private val listOfImages = mutableListOf<PhotoItem>()

    private var shouldReturnError = false

    fun setShouldReturnError(newValue: Boolean) {
        shouldReturnError = newValue
    }


    override suspend fun fetchElectroluxImages(callback: (FlickrResponseModel) -> Unit) {
        if (shouldReturnError) {

        } else {
            listOfImages.add(
                PhotoItem(
                    owner = null,
                    server = null,
                    ispublic = null,
                    isfriend = null,
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

    override suspend fun fetchSearchedImages(
        searchTag: String,
        callback: (FlickrResponseModel) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}
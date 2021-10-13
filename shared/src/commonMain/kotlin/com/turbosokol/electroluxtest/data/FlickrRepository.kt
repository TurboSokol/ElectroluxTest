package com.turbosokol.electroluxtest.data

import com.turbosokol.electroluxtest.network.FlickrApi
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface FlickrRepositoryInterface : KoinComponent {
    suspend fun fetchElectroluxImages(callback: (FlickrResponseModel) -> Unit)
    suspend fun fetchSearchedImages(searchTag: String, callback: (FlickrResponseModel) -> Unit)
}

class FlickrRepository: FlickrRepositoryInterface {

    private val flickrApi: FlickrApi by inject()

    override suspend fun fetchElectroluxImages(callback: (FlickrResponseModel) -> Unit) {
        try {
            flickrApi.fetchElectroluxImages { response ->
               callback(response)
            }
        } catch (e: Exception) {
            //Here we can connect logger and get error logs
        }
    }

    override suspend fun fetchSearchedImages(
        searchTag: String,
        callback: (FlickrResponseModel) -> Unit
    ) {
        try {
            flickrApi.fetchSearchedImages(searchTag) {
                callback(it)
            }
        } catch (e: Exception) {
            //Here we can connect logger and get error logs
        }
    }

}


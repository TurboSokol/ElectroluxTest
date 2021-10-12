package com.turbosokol.electroluxtest.data

import com.turbosokol.electroluxtest.network.FlickrApi
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface FlickrRepositoryInterface{
    suspend fun fetchElectroluxImages(callback: (FlickrResponseModel) -> Unit)
    suspend fun fetchSearchedImages(searchTag: String, callback: (FlickrResponseModel) -> Unit)
}

class FlickrRepository: KoinComponent, FlickrRepositoryInterface {

    private val flickrApi: FlickrApi by inject()

     override suspend fun fetchElectroluxImages(callback: (FlickrResponseModel) -> Unit) {
         try {
             flickrApi.fetchElectroluxImages {
                 callback(it)
             }
         } catch (e: Exception) {

         }
     }

    override suspend fun fetchSearchedImages(searchTag: String, callback: (FlickrResponseModel) -> Unit)  {
        try {
            flickrApi.fetchSearchedImages(searchTag) {
                callback(it)
            }
        } catch (e: Exception) {
        }
    }

}
package com.turbosokol.electroluxtest.data

import com.turbosokol.electroluxtest.network.FlickrApi
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FlickrRepository: KoinComponent {

    private val flickrApi: FlickrApi by inject()

     suspend fun fetchElectroluxImages() {
         flickrApi.fetchElectroluxImages {  flow: Flow<List<FlickrResponseModel>> ->
            //TODO::REBASE IN VIEWMODEL?
         }
    }

     suspend fun fetchSearchedImages(searchTag: String)  {
        flickrApi.fetchSearchedImages(searchTag) {

        }
    }

}
package com.turbosokol.electroluxtest.network

import com.turbosokol.electroluxtest.data.FlickrResponseModel
import com.turbosokol.electroluxtest.utils.*
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class FlickrApi(
    val ktorClient: HttpClient
) : KoinComponent {
    //App launch request with default search tag
     fun fetchElectroluxImages(callback: (FlickrResponseModel) -> Unit) {
        applicationNetworkScope.launch(expectedDispatcher) {
            val response: FlickrResponseModel =
                ktorClient.get("$BASE_URL$ELECTROLUX_TAG$DEFAULT_FLICKR_QUERY_ARGUMENTS")
            callback(response)
        }
    }

    //Dynamic request witch search image by tag from app search engine
    fun fetchSearchedImages(
        searchTag: String,
        callback: (FlickrResponseModel) -> Unit
    ) {
        applicationNetworkScope.launch(expectedDispatcher) {
            val response: FlickrResponseModel =
                ktorClient.get("$BASE_URL$searchTag$DEFAULT_FLICKR_QUERY_ARGUMENTS")
            callback(response)
        }
    }
}
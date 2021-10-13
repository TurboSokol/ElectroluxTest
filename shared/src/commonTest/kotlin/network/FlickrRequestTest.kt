package network

import com.turbosokol.electroluxtest.data.PhotoItem
import com.turbosokol.electroluxtest.di.initKoin
import com.turbosokol.electroluxtest.network.FlickrApi
import com.turbosokol.electroluxtest.utils.applicationNetworkScope
import kotlinx.coroutines.launch
import org.koin.core.Koin
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue


class FlickrRequestTest {

    private lateinit var koin: Koin
    private lateinit var flickrApi: FlickrApi

    @BeforeTest
    fun prepareForTesting() {
        koin = initKoin().koin
        flickrApi = koin.get<FlickrApi>()
    }

    @Test
    fun testForAppStartRequestingImagesFromFlickrApi() {
        var resultList = emptyList<PhotoItem?>()

        applicationNetworkScope.launch {
            flickrApi.fetchElectroluxImages { response ->
                resultList = response.photos?.photo!!
            }
            assertTrue(resultList.isNotEmpty())
        }
    }



    //Generate a random string and doing network request with it
    @Test
    fun testForDynamicRequestImagesFromFlickrApi() {
        var resultList = emptyList<PhotoItem?>()
        val randomString = mutableListOf<Char>()
        (1..5).map {
            val char = (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
            randomString.add(char)
        }
        val searchTag = randomString.joinToString("")

        applicationNetworkScope.launch {
            flickrApi.fetchSearchedImages(searchTag) { response ->
                resultList = response.photos?.photo!!
            }
            assertTrue(resultList.isNotEmpty())
        }
    }

    //Generate a HUGE random string and doing network request with it
    @Test
    fun testForDynamicRequestImagesFromFlickrApiWithLongString() {
        var resultList = emptyList<PhotoItem?>()
        val randomString = mutableListOf<Char>()
        (1..42).map {
            val char = (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
            randomString.add(char)
        }
        val searchTag = randomString.joinToString("")
        println("SEARCH TAG IS $searchTag")
        applicationNetworkScope.launch {
            flickrApi.fetchSearchedImages(searchTag) { response ->
                resultList = response.photos!!.photo!!
            }
            assertTrue(resultList.isNotEmpty())
        }
    }
}
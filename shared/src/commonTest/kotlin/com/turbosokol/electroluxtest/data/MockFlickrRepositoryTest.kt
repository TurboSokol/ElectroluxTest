import com.turbosokol.electroluxtest.data.FlickrResponseModel
import org.koin.core.logger.Logger
import kotlin.test.Test

class MockFlickrRepositoryTest() {

    private val observablePhotoList = listOf<FlickrResponseModel>()

    private var shouldReturnError = false

    fun shouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    fun generateString(): String {
        val testCharSequence = mutableListOf<Char>()
        for (index in 0..42) {
              val newChar = (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
            testCharSequence.add(newChar)
        }
        return testCharSequence.joinToString()
    }

    fun pr() {
        val txt = generateString()
        println("MYGIBLOGGER"+txt)
    }

    @Test
    fun fetchElectroluxImages() {

    }

    @Test
    fun fetchSearchedImages() {
    }
}
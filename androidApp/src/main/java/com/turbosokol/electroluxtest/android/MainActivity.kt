package com.turbosokol.electroluxtest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.turbosokol.electroluxtest.android.ui.theme.ElectroluxTestTheme
import com.turbosokol.electroluxtest.android.viewmodels.FlickrViewModel
import com.turbosokol.electroluxtest.data.PhotoItem

class MainActivity : ComponentActivity() {

    //lazy initialization of view model - it will be init at the moment when it invoking
    //this view model collect all data about flickr images service
    private val flickViewModel: FlickrViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Observable MutableState
            val imagesList = flickViewModel.imageList.value

            ElectroluxTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    if (imagesList.isEmpty()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colors.secondary)
                        }
                    }

                    if (imagesList.isNotEmpty()) {
                        MainNavigation(imagesList)
                    }

                }
            }
        }
    }
}

@Composable
fun MainNavigation(list: List<PhotoItem?>) {
    val navController = rememberNavController()

}
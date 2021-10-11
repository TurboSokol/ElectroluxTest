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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.turbosokol.electroluxtest.android.ui.screens.DetailScreen
import com.turbosokol.electroluxtest.android.ui.screens.MainScreen
import com.turbosokol.electroluxtest.android.ui.screens.ScreensRoutes
import com.turbosokol.electroluxtest.android.ui.theme.ElectroluxTestTheme
import com.turbosokol.electroluxtest.android.viewmodels.FlickrViewModel
import com.turbosokol.electroluxtest.data.PhotoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity() : ComponentActivity() {

    //Scope for launchUI
    private val myUiScope = CoroutineScope(Dispatchers.Main + Job())

    //lazy initialization of view model - it will be init at the moment when it invoking
    //this view model collect all data about flickr images service
    private val flickViewModel: FlickrViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //GET DEFAULT FLICKR REQUEST
        fetchData()

        myUiScope.launch {
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

     private fun fetchData() {
        flickViewModel.fetchElectroluxImages()
    }
}

@ExperimentalComposeUiApi
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreensRoutes.MainScreen.route) {
        composable(route = ScreensRoutes.MainScreen.route) {
            MainScreen(navController = navController, )
        }
        composable(route = ScreensRoutes.DetailScreen.route) {
            it.arguments?.getString("url").let { url ->
                DetailScreen(url = url)
            }

        }
    }

}
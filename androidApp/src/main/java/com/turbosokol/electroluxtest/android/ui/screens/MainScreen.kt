package com.turbosokol.electroluxtest.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.turbosokol.electroluxtest.android.R
import com.turbosokol.electroluxtest.android.viewmodels.FlickrViewModel
import com.turbosokol.electroluxtest.data.PhotoItem
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    navController: NavController,
    imagesList: PhotoItem?,
    viewModel: FlickrViewModel = getViewModel()
) {

    val searchTag = viewModel.searchTag.value
    val imagesList = viewModel.imageList.value
    val keyboard = LocalSoftwareKeyboardController.current




    Column {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                elevation = 8.dp
            )
            {

                Row(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        value = searchTag,
                        onValueChange = { newValue ->
                            viewModel.onSearchTagChanged(newValue)
                        },
                        label = { Text(text = stringResource(id = R.string.search)) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search,
                        ),
                        leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.ic_baseline_search_24),
                                contentDescription = null
                            )
                        },
                        keyboardActions = KeyboardActions(onSearch = {
                            viewModel.fetchSearchedImages(searchTag)
                            keyboard?.hide()
                        })
                    )
                }
            }

            LazyColumn() {
                itemsIndexed(items = imagesList) { index, item ->
                    ImageCard(index, item, onClick = {
                        navController.navigate(ScreensRoutes.DetailScreen.createRoute(item?.url_m))
                    })
                }
            }

        }

}
package com.turbosokol.electroluxtest.android.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.rememberImagePainter
import com.turbosokol.electroluxtest.android.R
import com.turbosokol.electroluxtest.android.ui.theme.ElectroluxTestTheme
import com.turbosokol.electroluxtest.android.utils.*
import com.turbosokol.electroluxtest.android.viewmodels.FlickrViewModel
import com.turbosokol.electroluxtest.data.PhotoItem
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

@ExperimentalComposeUiApi
class ImageListFragment : Fragment() {
    private val flickrViewModel: FlickrViewModel by sharedViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Load data before launch UI
        val appStartFetchingFlag = flickrViewModel.appStartFetchingFlag.value
        if (!appStartFetchingFlag){
            appStartDataFetching()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ImagesListScreen()
            }
        }
    }

    @Composable
    fun ImagesListScreen() {
        val keyboard = LocalSoftwareKeyboardController.current
        //Observable MutableState
        val imagesList = flickrViewModel.imageList.value
        val searchTag = flickrViewModel.searchTag.value

        ElectroluxTestTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {

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
                                    flickrViewModel.onSearchTagChanged(newValue)
                                },
                                label = { Text(text = stringResource(id = R.string.search_bar_label)) },
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
                                    //Clearing image list for animation
                                    flickrViewModel.clearImageList()
                                    flickrViewModel.fetchSearchedImages(searchTag) {
                                        localMainScope.launch {
                                            showToast(getString(R.string.search_error_toast))
                                        }
                                    }
                                    keyboard?.hide()
                                    flickrViewModel.onSearchTagChanged("")
                                })
                            )
                        }
                    }

                    //Shows progress bar until wait response
                    if (imagesList.isEmpty()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colors.secondary)
                        }
                    }

                    //Show content after response loaded
                    else {
                        //Recycler view
                        LazyColumn {
                            itemsIndexed(items = imagesList) { index, item ->
                                ImageCard(index, item, onClick = {
                                    flickrViewModel.switchOnSelected(true)
                                    flickrViewModel.setIndex(index)
                                })
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ImageCard(index: Int, photoItem: PhotoItem?, onClick: () -> Unit) {
        val itemIndex = flickrViewModel.itemIndex.value
        val onSelected = flickrViewModel.onSelected.value

        //Context for bitmap operations
        val localContext = LocalContext.current

        //Highlight card when it selected
        var cardBorder: BorderStroke? = null

        cardBorder = if (index == itemIndex && onSelected) {
            BorderStroke(3.dp, MaterialTheme.colors.secondary)
        } else null

        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(bottom = 6.dp, top = 6.dp)
                .fillMaxWidth()
                .clickable(onClick = onClick),
            elevation = 8.dp,
            border = cardBorder
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = photoItem?.url_m, builder = {
                        placeholder(PLACEHOLDER_IMAGE)
                    }),
                    contentDescription = stringResource(R.string.default_content_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.FillWidth
                )

                //Show button when item is clicked
                if (index == itemIndex && onSelected) {
                    Button(modifier = Modifier.fillMaxWidth(), onClick = {
                        localIoScope.launch {
                            val bitmap = getBitmapFromUrl(photoItem?.url_m, localContext)
                            savePictureToGallery(bitmap, localContext) {
                                //onSuccess saving picture callback
                                localMainScope.launch {
                                    flickrViewModel.switchOnSelected(false)
                                    showToast(getString(R.string.save_to_gallery_onsuccess_toast))
                                }
                            }
                        }
                    }) {
                        Text(
                            text = stringResource(R.string.download_image_label),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun appStartDataFetching() {
        flickrViewModel.fetchElectroluxImages()
        flickrViewModel.appStartFetchingFlagSwitcher()
    }
}


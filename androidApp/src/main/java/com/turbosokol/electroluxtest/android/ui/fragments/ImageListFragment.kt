package com.turbosokol.electroluxtest.android.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.turbosokol.electroluxtest.android.R
import com.turbosokol.electroluxtest.android.ui.items.ImageCard
import com.turbosokol.electroluxtest.android.ui.theme.ElectroluxTestTheme
import com.turbosokol.electroluxtest.android.viewmodels.FlickrViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

@ExperimentalComposeUiApi
class ImageListFragment : Fragment() {
    private val flickrViewModel: FlickrViewModel by sharedViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Load data before launch UI
        fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val keyboard = LocalSoftwareKeyboardController.current
                //Observable MutableState
                val imagesList = flickrViewModel.imageList.value
                val searchTag = flickrViewModel.searchTag.value

                ElectroluxTestTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
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
                        if (imagesList.isNotEmpty()) {
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
                                                //Clearing image list for animation
                                                flickrViewModel.clearImageList()
                                                flickrViewModel.fetchSearchedImages(searchTag)
                                                keyboard?.hide()
                                                flickrViewModel.onSearchTagChanged("")
                                            })
                                        )
                                    }
                                }
                                //Recycler view
                                LazyColumn() {
                                    itemsIndexed(items = imagesList) { index, item ->
                                        ImageCard(index, item, onClick = {
                                            //Send url to detail screen
                                            val bundle = Bundle().apply {
                                                putString("photoUrl", item?.url_m)
                                            }
                                            findNavController().navigate(R.id.action_imageListFragment_to_detailFragment, bundle)
                                        })
                                    }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        private fun fetchData() {
            flickrViewModel.fetchElectroluxImages()
        }
    }


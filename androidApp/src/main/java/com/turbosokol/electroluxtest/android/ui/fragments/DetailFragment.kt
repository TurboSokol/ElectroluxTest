package com.turbosokol.electroluxtest.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import com.turbosokol.electroluxtest.android.R
import com.turbosokol.electroluxtest.android.utils.PLACEHOLDER_IMAGE
import com.turbosokol.electroluxtest.android.viewmodels.FlickrViewModel

class DetailFragment(

) : Fragment() {

    private val flickrViewModel: FlickrViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Get url from List fragment and set to observable
        arguments?.getString("photoUrl")?.let {
            flickrViewModel.setPhotoUrl(it)
        }

        return ComposeView(requireContext()).apply {
            //Observable url
            val photoUrl = flickrViewModel.photoUrl.value
            setContent {
                Card {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = rememberImagePainter(data = photoUrl, builder = {
                                placeholder(PLACEHOLDER_IMAGE)
                            }),
                            contentDescription = stringResource(R.string.default_content_description),
                            modifier = Modifier.size(1024.dp)
                        )
                    }
                }
            }
        }
    }
}
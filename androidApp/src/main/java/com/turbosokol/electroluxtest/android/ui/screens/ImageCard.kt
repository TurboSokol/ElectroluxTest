package com.turbosokol.electroluxtest.android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.turbosokol.electroluxtest.android.R
import com.turbosokol.electroluxtest.android.utils.PLACEHOLDER_IMAGE
import com.turbosokol.electroluxtest.data.PhotoItem

@Composable
fun ImageCard(photoItem: PhotoItem, onClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 6.dp, top = 6.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Row(modifier = Modifier.padding(4.dp)) {

                Image(
                    painter = rememberImagePainter(data = photoItem.url_m, builder = {
                        placeholder(PLACEHOLDER_IMAGE)
                    }),
                    contentDescription = stringResource(R.string.default_content_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.FillWidth
                )
        }

    }
}
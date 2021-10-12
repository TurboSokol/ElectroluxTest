package com.turbosokol.electroluxtest.android.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.turbosokol.electroluxtest.android.utils.PLACEHOLDER_IMAGE

@Composable
fun DetailScreen(url: String?) {
    Card {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(data = url, builder = {
                    placeholder(PLACEHOLDER_IMAGE)
                }),
                contentDescription = stringResource(com.turbosokol.electroluxtest.android.R.string.default_content_description),
                modifier = Modifier.size(1024.dp)
            )
        }
    }
}
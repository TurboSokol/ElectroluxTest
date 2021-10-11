package com.turbosokol.electroluxtest.android.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.turbosokol.electroluxtest.data.PhotoItem

@Composable
fun MainScreen(
    navController: NavController,
    items: List<PhotoItem?>
) {
    Scaffold(content = {
        LazyColumn() {
            itemsIndexed(items = items) { index, item ->
                //TODO::IMAGE CARD WITH ONCLICK
            }
        }
    })
}
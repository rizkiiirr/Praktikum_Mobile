package com.example.scrollablelistcompose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import com.example.scrollablelistcompose.data.DataProvider
import androidx.compose.ui.Modifier
import com.example.scrollablelistcompose.data.Chocolate


@Composable
fun ScrollAbleListHomeContent(modifier: Modifier = Modifier, onDetailClick: (Chocolate) -> Unit = {}) {
    val chocolates = remember { DataProvider.chocolateList }
    LazyColumn(modifier = modifier) {
        items(chocolates) { chocolate ->
            ChocolateListItem(chocolate, onDetailClick = {onDetailClick(it)})
        }
    }
}
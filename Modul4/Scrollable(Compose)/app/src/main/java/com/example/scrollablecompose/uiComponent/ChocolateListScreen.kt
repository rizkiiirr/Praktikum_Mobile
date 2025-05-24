package com.example.scrollablecompose.uiComponent

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.scrollablecompose.data.Chocolate

@Composable
fun ListScreen(
    chocolates: List<Chocolate>,
    onDetailClicked: (Chocolate) -> Unit
) {
    LazyColumn {
        items(chocolates) { chocolate ->
            ChocolateItem(chocolate = chocolate, onDetailClicked = onDetailClicked)
        }
    }
}

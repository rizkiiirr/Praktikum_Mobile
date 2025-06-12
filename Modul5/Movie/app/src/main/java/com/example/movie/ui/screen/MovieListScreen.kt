package com.example.movie.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.movie.domain.model.Movie
import com.example.movie.ui.component.MovieItem

@Composable
fun MovieListScreen(
    movies: List<Movie>,
    onDetailClick: (Int) -> Unit
) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(items = movies, key = { it.id }) { movie ->
                MovieItem(
                    movie = movie,
                    onDetailClick = onDetailClick
                )
            }
        }
}
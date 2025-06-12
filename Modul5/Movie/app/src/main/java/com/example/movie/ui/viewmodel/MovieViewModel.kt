package com.example.movie.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.usecase.GetPopularMoviesUseCase
import com.example.movie.ui.screen.MovieListState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MovieViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    val state: StateFlow<MovieListState> = getPopularMoviesUseCase()
        .map { result ->
            result.fold(
                onSuccess = { movies ->
                    MovieListState(
                        isLoading = false,
                        movies = movies
                    )
                },
                onFailure = { error ->
                    MovieListState(
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = MovieListState(isLoading = true)
        )
}
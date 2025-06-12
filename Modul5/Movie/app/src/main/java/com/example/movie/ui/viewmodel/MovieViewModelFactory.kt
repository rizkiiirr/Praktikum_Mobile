package com.example.movie.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.domain.usecase.GetPopularMoviesUseCase

class MovieViewModelFactory(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(getPopularMoviesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
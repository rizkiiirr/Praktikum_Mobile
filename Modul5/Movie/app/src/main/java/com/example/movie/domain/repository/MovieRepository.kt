package com.example.movie.domain.repository

import com.example.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<Result<List<Movie>>>
}
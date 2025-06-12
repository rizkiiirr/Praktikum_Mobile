package com.example.movie.domain.usecase

import com.example.movie.domain.model.Movie
import com.example.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(
    private val repository: MovieRepository
) {

    operator fun invoke(): Flow<Result<List<Movie>>> {
        return repository.getPopularMovies()
    }
}
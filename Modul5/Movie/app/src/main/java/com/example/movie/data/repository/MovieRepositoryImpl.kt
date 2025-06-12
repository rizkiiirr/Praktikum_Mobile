package com.example.movie.data.repository

import android.util.Log
import com.example.movie.data.local.MovieDatabase
import com.example.movie.data.remote.ApiService
import com.example.movie.data.toDomain
import com.example.movie.data.toEntity
import com.example.movie.domain.model.Movie
import com.example.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val db: MovieDatabase
) : MovieRepository {

    private val dao = db.movieDao()

    override fun getPopularMovies(): Flow<Result<List<Movie>>> = flow {
        val cachedMovies = dao.getAllMovies().first().map { it.toDomain() }
        emit(Result.success(cachedMovies))

        try {
            val response = apiService.getPopularMovies(apiKey = "33e3d3edb5f09019aeba353631ab45a9")
            Log.d("MOVIE_APP_DEBUG", "API Response: Received ${response.results.size} movies from network.")
            val movieEntities = response.results.map { it.toEntity() }
            dao.clearAll()
            dao.insertAll(movieEntities)

            val newCachedMovies = dao.getAllMovies().first().map { it.toDomain() }
            emit(Result.success(newCachedMovies))

        } catch (e: Exception) {
            Log.e("MOVIE_APP_DEBUG", "Gagal mengambil data dari API. Error: ", e)

            emit(Result.failure(e))
        }
    }
}
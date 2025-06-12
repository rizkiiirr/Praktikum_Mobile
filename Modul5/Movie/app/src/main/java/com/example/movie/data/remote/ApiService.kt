package com.example.movie.data.remote

import com.example.movie.data.remote.datatransferobjects.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieListResponse
}
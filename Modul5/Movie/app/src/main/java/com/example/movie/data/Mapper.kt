package com.example.movie.data

import com.example.movie.data.local.MovieEntity
import com.example.movie.data.remote.datatransferobjects.MovieResult
import com.example.movie.domain.model.Movie

fun MovieResult.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterUrl = "https://image.tmdb.org/t/p/w500${this.posterPath}",
        releaseDate = this.releaseDate
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterUrl = this.posterUrl,
        releaseDate = this.releaseDate
    )
}
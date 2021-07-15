package com.awalideck.moovies.models

import com.squareup.moshi.Json

data class PopularMovies(val page: Int, val results: List<Movie>)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    @Json(name = "poster_path")
    val posterPath: String
)
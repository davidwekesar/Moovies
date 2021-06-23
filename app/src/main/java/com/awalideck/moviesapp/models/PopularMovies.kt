package com.awalideck.moviesapp.models

import com.squareup.moshi.Json

data class PopularMovies(val page: Int, val results: List<Movie>)

data class Movie(
    val title: String,
    val overview: String,

    @Json(name = "release_date")
    val releaseDate: String? = null
)
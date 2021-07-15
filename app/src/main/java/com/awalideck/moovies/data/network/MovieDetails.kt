package com.awalideck.moovies.data.network

import com.squareup.moshi.Json

data class MovieDetails(
    @Json(name = "backdrop_path")
    val backdropPath: String,

    val genres: List<Genre>,
    val overview: String,

    @Json(name = "poster_path")
    val posterPath: String,

    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany>,

    @Json(name = "release_date")
    val releaseDate: String,

    val runtime: Int,
    val status: String,
    val title: String,

    @Json(name = "vote_average")
    val voteAverage: Double
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,

    @Json(name = "logo_path")
    val logoPath: String? = null,

    val name: String
)
package com.awalideck.moovies.data.network

import com.awalideck.moovies.BuildConfig
import com.awalideck.moovies.models.PopularMovies
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.themoviedb.org/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {

    @GET("3/movie/{category}?api_key=${BuildConfig.MOVIEDB_KEY}&language=en-US&page=1")
    suspend fun getPopularMovies(@Path("category") category: String): PopularMovies

    @GET("3/movie/{movieId}?api_key=${BuildConfig.MOVIEDB_KEY}&language=en-US")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetails
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
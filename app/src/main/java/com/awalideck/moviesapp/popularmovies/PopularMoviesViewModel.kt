package com.awalideck.moviesapp.popularmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.awalideck.moviesapp.MovieApi
import com.awalideck.moviesapp.models.Movie
import com.awalideck.moviesapp.models.PopularMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        MovieApi.retrofitService.getPopularMovies().enqueue(object : Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                response.body()?.let { popularMovies ->
                    _movies.value = popularMovies.results
                }
            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Log.e(TAG, "Failure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "PopularMoviesViewModel"
    }
}
package com.awalideck.moviesapp.popularmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awalideck.moviesapp.MovieApi
import com.awalideck.moviesapp.models.Movie
import kotlinx.coroutines.launch

class PopularMoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                val popularMovies = MovieApi.retrofitService.getPopularMovies()
                _movies.value = popularMovies.results
            } catch (e: Exception) {
                Log.e(TAG, "Failure: ${e.message}")
            }
        }
    }

    companion object {
        private const val TAG = "PopularMoviesViewModel"
    }
}
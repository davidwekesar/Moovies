package com.awalideck.moovies.popularmovies

import android.util.Log
import androidx.lifecycle.*
import com.awalideck.moovies.data.network.MovieApi
import com.awalideck.moovies.models.Movie
import kotlinx.coroutines.launch
import java.lang.Exception

class MoviesViewModel(private val path: String) : ViewModel() {

    private val _response = MutableLiveData<List<Movie>>()
    val response: LiveData<List<Movie>> get() = _response

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val movies = MovieApi.retrofitService.getPopularMovies(path)
                Log.d("PopularMoviesViewModel", "Response: $movies")
                _response.value = movies.results
            }catch (e: Exception) {
                Log.d("PopularMoviesViewModel", "Failure: ${e.message}")
            }
        }
    }
}

class MoviesViewModelFactory(private val path: String): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(path) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
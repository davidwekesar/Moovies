package com.awalideck.moviesapp.details

import android.util.Log
import androidx.lifecycle.*
import com.awalideck.moviesapp.data.network.MovieApi
import com.awalideck.moviesapp.data.network.MovieDetails
import kotlinx.coroutines.launch

class DetailsViewModel(private val movieId: Int) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> get() = _movieDetails

    init {
        getMovieDetails()
    }

    private fun getMovieDetails() {
        viewModelScope.launch {
            try {
                val result = MovieApi.retrofitService.getMovieDetails(movieId)
                Log.d(TAG, "Response: $result")
                _movieDetails.value = result
            } catch (e: Exception) {
                Log.d(TAG, "Failure: ${e.message}")
            }
        }
    }

    companion object {
        private const val TAG = "DetailsViewModel"
    }
}

class DetailsViewModelFactory(private val movieId: Int): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(movieId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
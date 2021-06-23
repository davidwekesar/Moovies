package com.awalideck.moviesapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title
    private val _releaseDate = MutableLiveData<String>()
    val releaseDate: LiveData<String> get() = _releaseDate
    private val _apiStatus = MutableLiveData<Boolean>()
    val apiStatus: LiveData<Boolean> get() = _apiStatus
    private val _guestSessionID = MutableLiveData<String>()
    val guestSessionID: LiveData<String> get() = _guestSessionID
    private val TAG = this.javaClass.simpleName

    init {
//        getMovieInfo()
        createGuestSession()
    }

    private fun getMovieInfo() {
        val apiKey = BuildConfig.MOVIEDB_KEY
        MovieApi.retrofitService.getMovieInfo(apiKey).enqueue(
            object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    _title.value = response.body()?.title
                    _releaseDate.value = response.body()?.releaseDate
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.e(TAG, "Failure: ${t.message}")
                }
            }
        )
    }

    private fun createGuestSession() {
        MovieApi.retrofitService.getGuestSessionID().enqueue(object : Callback<GuestSession>{
            override fun onResponse(call: Call<GuestSession>, response: Response<GuestSession>) {
                _apiStatus.value = response.body()?.success
                _guestSessionID.value = response.body()?.guestSessionID
            }

            override fun onFailure(call: Call<GuestSession>, t: Throwable) {
                Log.e(TAG, "Failure: ${t.message}")
            }
        })
    }
}

data class Movie(
    val title: String,

    @Json(name = "release_date")
    val releaseDate: String
)

data class GuestSession(
    val success: Boolean,

    @Json(name = "guest_session_id")
    val guestSessionID: String,

    @Json(name = "expires_at")
    val expirationTime: String
)
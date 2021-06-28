package com.awalideck.moviesapp.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.awalideck.moviesapp.data.network.MovieApi

class PopularMoviesViewModel : ViewModel() {

    val flow = Pager(PagingConfig(20)) {
        MoviePagingSource(MovieApi.retrofitService)
    }.flow.cachedIn(viewModelScope)
}
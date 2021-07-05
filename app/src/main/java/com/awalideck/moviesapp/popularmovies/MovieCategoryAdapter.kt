package com.awalideck.moviesapp.popularmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MovieCategoryAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        val fragment = CategoryMovieFragment()
        val fragmentName = when(position) {
            0 -> KEY_NOW_PLAYING
            1 -> KEY_UPCOMING
            2 -> KEY_POPULAR
            else -> KEY_TOP_RATED
        }
        fragment.arguments = Bundle().apply {
            putString(ARG_CATEGORY_PATH, fragmentName)
        }
        return fragment
    }

    companion object {
        private const val KEY_NOW_PLAYING = "now_playing"
        private const val KEY_UPCOMING = "upcoming"
        private const val KEY_POPULAR = "popular"
        private const val KEY_TOP_RATED = "top_rated"
    }
}

const val ARG_CATEGORY_PATH = "category"
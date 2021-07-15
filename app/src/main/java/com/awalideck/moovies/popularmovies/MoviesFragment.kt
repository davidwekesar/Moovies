package com.awalideck.moovies.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.awalideck.moovies.R
import com.awalideck.moovies.databinding.FragmentMoviesBinding
import com.google.android.material.tabs.TabLayoutMediator

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieCategoryAdapter: MovieCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        movieCategoryAdapter = MovieCategoryAdapter(this)
        binding.viewPager.adapter = movieCategoryAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = getString(R.string.now_playing)
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_now_playing_24)
                }
                1 -> {
                    tab.text = getString(R.string.upcoming)
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_upcoming_24)
                }
                2 -> {
                    tab.text = getString(R.string.popular)
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_popular_24)
                }
                3 -> {
                    tab.text = getString(R.string.top_rated)
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_top_rated_24)
                }
            }
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
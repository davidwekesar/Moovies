package com.awalideck.moviesapp.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.awalideck.moviesapp.databinding.FragmentCategoryMovieBinding

class CategoryMovieFragment : Fragment() {

    private var _binding: FragmentCategoryMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MoviesViewModel
    private lateinit var viewModelFactory: MoviesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryMovieBinding.inflate(inflater, container, false)

        arguments?.takeIf { it.containsKey(ARG_CATEGORY_PATH) }?.apply {
            val path = getString(ARG_CATEGORY_PATH)
            path?.let {
                viewModelFactory = MoviesViewModelFactory(it)
            }
        }
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MoviesViewModel::class.java)

        viewModel.response.observe(viewLifecycleOwner, { movies ->
            val movieAdapter = MovieAdapter(requireContext(), movies, MovieItemListener { movieId ->
                val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsActivity(movieId)
                findNavController().navigate(action)
            })
            binding.movieList.adapter = movieAdapter
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
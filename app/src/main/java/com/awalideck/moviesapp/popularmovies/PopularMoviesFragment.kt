package com.awalideck.moviesapp.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadStateAdapter
import com.awalideck.moviesapp.databinding.FragmentPopularMoviesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularMoviesFragment : Fragment() {

    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PopularMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)

        val pagingAdapter = MovieAdapter(requireContext(), MovieComparator)
        binding.moviesRecyclerView.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
            header = MovieLoadStateAdapter { pagingAdapter.retry() },
            footer = MovieLoadStateAdapter { pagingAdapter.retry() }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
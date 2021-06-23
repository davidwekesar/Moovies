package com.awalideck.moviesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.awalideck.moviesapp.databinding.FragmentSearchBinding
import java.text.SimpleDateFormat

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val model: SearchViewModel by viewModels()
//        model.title.observe(viewLifecycleOwner, { title ->
//            binding.titleTextView.text = title
//        })
//        model.releaseDate.observe(viewLifecycleOwner, { releaseDate ->
//            binding.yearTextView.text = formatDate(releaseDate)
//        })

        model.apiStatus.observe(viewLifecycleOwner, { status ->
            binding.titleTextView.text = status.toString()
        })
        model.guestSessionID.observe(viewLifecycleOwner, { sessionID ->
            binding.yearTextView.text = sessionID
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
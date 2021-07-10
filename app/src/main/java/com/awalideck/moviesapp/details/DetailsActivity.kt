package com.awalideck.moviesapp.details

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.awalideck.moviesapp.R
import com.awalideck.moviesapp.databinding.ActivityDetailsBinding
import com.awalideck.moviesapp.utils.getPosterURL
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val args: DetailsActivityArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels { DetailsViewModelFactory(args.movieID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.movieDetails.observe(this, { movieDetails ->
            setMoviePoster(movieDetails.backdropPath, binding.poster)
            binding.toolbarLayout.title = movieDetails.title
            binding.contentDetails.overview.text = movieDetails.overview
            val productionCompaniesAdapter =
                ProductionCompaniesAdapter(movieDetails.productionCompanies, this)
            binding.contentDetails.listProductionCompanies.adapter = productionCompaniesAdapter
        })
    }

    private fun setMoviePoster(backdropPath: String, posterImageView: ImageView) {
        Picasso.get()
            .load(getPosterURL(backdropPath))
            .placeholder(R.drawable.ic_image_24)
            .error(R.drawable.ic_broken_image_24)
            .into(posterImageView)
    }
}
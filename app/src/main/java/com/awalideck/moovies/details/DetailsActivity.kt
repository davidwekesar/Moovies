package com.awalideck.moovies.details

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.awalideck.moovies.R
import com.awalideck.moovies.data.network.MovieDetails
import com.awalideck.moovies.databinding.ActivityDetailsBinding
import com.awalideck.moovies.utils.getPosterURL
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
            setValues(movieDetails)
        })
    }

    private fun setValues(movieDetails: MovieDetails) {
        setMoviePoster(movieDetails.backdropPath, binding.poster)
        binding.toolbarLayout.title = movieDetails.title
        binding.contentDetails.overview.text = movieDetails.overview
        binding.contentDetails.textVoteAverage.text = movieDetails.voteAverage.toString()
        binding.contentDetails.ratingBar.rating = getVoteAverage(movieDetails.voteAverage)
        binding.contentDetails.textRuntime.text = getRunTime(movieDetails.runtime)
        binding.contentDetails.textYear.text = getYear(movieDetails.releaseDate)
        binding.contentDetails.textStatus.text = movieDetails.status
        val productionCompaniesAdapter =
            ProductionCompaniesAdapter(movieDetails.productionCompanies, this)
        binding.contentDetails.listProductionCompanies.adapter = productionCompaniesAdapter
    }

    private fun setMoviePoster(backdropPath: String, posterImageView: ImageView) {
        Picasso.get()
            .load(getPosterURL(backdropPath))
            .resizeDimen(R.dimen.backdrop_image_width, R.dimen.backdrop_image_height)
            .onlyScaleDown()
            .placeholder(R.drawable.ic_image_24)
            .error(R.drawable.ic_broken_image_24)
            .into(posterImageView)
    }

    private fun getYear(date: String): String {
        val subStrings = date.split("-")
        return subStrings[0]
    }

    private fun getRunTime(runtime: Int): String {
        val hour = runtime / 60
        val min = runtime % 60
        return getString(R.string.time_format, hour, min)
    }

    private fun getVoteAverage(voteAverage: Double): Float {
        val newVoteAverage = voteAverage / 2
        return newVoteAverage.toFloat()
    }
}
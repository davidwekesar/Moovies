package com.awalideck.moviesapp.popularmovies

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.awalideck.moviesapp.R
import com.awalideck.moviesapp.databinding.ListItemMovieBinding
import com.awalideck.moviesapp.models.Movie
import com.awalideck.moviesapp.utils.formatDate
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        with(movie) {
            holder.titleTextView.text = title
            holder.overviewTextView.text = overview
            holder.releaseDateTV.text = setTheReleaseDate(releaseDate)
            Picasso.get().load(getPosterURL(posterPath)).into(holder.posterImageView)
        }
    }

    override fun getItemCount(): Int = movies.size

    /**
     * Checks whether the release date string received from the API is null.
     * If null return "TBA", otherwise, return a formatted date string.
     */
    private fun setTheReleaseDate(releaseDate: String?): String {
        return if (releaseDate == null) context.getString(R.string.to_be_announced) else formatDate(
            releaseDate
        )
    }

    private fun getPosterURL(path: String): Uri {
        val uriBuilder = Uri.Builder()
        uriBuilder.apply {
            scheme("https")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath("original")
                .appendPath(path)
        }
        return uriBuilder.build()
    }
}
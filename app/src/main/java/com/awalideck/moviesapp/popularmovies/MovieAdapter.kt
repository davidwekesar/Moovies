package com.awalideck.moviesapp.popularmovies

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.awalideck.moviesapp.R
import com.awalideck.moviesapp.databinding.ListItemMovieBinding
import com.awalideck.moviesapp.models.Movie
import com.awalideck.moviesapp.utils.formatDate
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Context, diffCallback: DiffUtil.ItemCallback<Movie>) :
    PagingDataAdapter<Movie, MovieViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            with(movie) {
                holder.titleTextView.text = title
                holder.overviewTextView.text = overview
                holder.releaseDateTV.text = setTheReleaseDate(releaseDate)
                Picasso.get()
                    .load(getPosterURL(posterPath))
                    .resize(80, 120)
                    .placeholder(R.drawable.ic_image_24)
                    .error(R.drawable.ic_broken_image_24)
                    .into(holder.posterImageView)
            }
        }
    }

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

object MovieComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
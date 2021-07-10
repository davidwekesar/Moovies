package com.awalideck.moviesapp.popularmovies

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.awalideck.moviesapp.R
import com.awalideck.moviesapp.databinding.ListItemMovieBinding
import com.awalideck.moviesapp.models.Movie
import com.awalideck.moviesapp.utils.formatDate
import com.awalideck.moviesapp.utils.getPosterURL
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val context: Context,
    private val movieList: List<Movie>,
    private val movieItemListener: MovieItemListener
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        with(movie) {
            holder.titleTextView.text = title
            holder.overviewTextView.text = overview
            holder.releaseDateTV.text = setTheReleaseDate(releaseDate)
            Picasso.get()
                .load(getPosterURL(posterPath))
                .resizeDimen(R.dimen.poster_image_width, R.dimen.poster_image_height)
                .onlyScaleDown()
                .placeholder(R.drawable.ic_image_24)
                .error(R.drawable.ic_broken_image_24)
                .into(holder.posterImageView)
        }
        holder.moreButton.setOnClickListener {
            movieItemListener.onClick(movie)
        }
    }

    /**
     * Checks whether the release date string received from the API is null and returns a string.
     */
    private fun setTheReleaseDate(releaseDate: String?): String {
        return if (releaseDate == null) context.getString(R.string.to_be_announced) else formatDate(
            releaseDate
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MovieItemListener(val clickListener: (movieId: Int) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie.id)
}

object MovieComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
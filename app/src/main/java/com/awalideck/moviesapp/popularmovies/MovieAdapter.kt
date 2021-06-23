package com.awalideck.moviesapp.popularmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.awalideck.moviesapp.databinding.ListItemMovieBinding
import com.awalideck.moviesapp.models.Movie
import com.awalideck.moviesapp.utils.formatDate

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.title
        holder.overviewTextView.text = movie.overview
        movie.releaseDate?.let { releaseDate ->
            holder.releaseDateTV.text = formatDate(releaseDate)
        }

    }

    override fun getItemCount(): Int = movies.size
}
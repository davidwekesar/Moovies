package com.awalideck.moovies.popularmovies

import androidx.recyclerview.widget.RecyclerView
import com.awalideck.moovies.databinding.ListItemMovieBinding

class MovieViewHolder(binding: ListItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
    val titleTextView = binding.titleTextView
    val overviewTextView = binding.overviewTextView
    val releaseDateTV = binding.releaseDateTV
    val posterImageView = binding.posterImageView
    val moreButton = binding.moreButton
}
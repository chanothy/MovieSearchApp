package com.example.moviesearchapp

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesearchapp.model.MovieInfo


class MovieDiffItemCallback : DiffUtil.ItemCallback<MovieInfo>() {
    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo)
            = (oldItem.Title == newItem.Title)
    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo) = (oldItem == newItem)
}
package com.example.moviesearchapp

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesearchapp.model.YelpRestaurant


class MovieDiffItemCallback : DiffUtil.ItemCallback<YelpRestaurant>() {
    override fun areItemsTheSame(oldItem: YelpRestaurant, newItem: YelpRestaurant)
            = (oldItem.Title == newItem.Title)
    override fun areContentsTheSame(oldItem: YelpRestaurant, newItem: YelpRestaurant) = (oldItem == newItem)
}
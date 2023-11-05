package com.example.moviesearchapp.model

import com.google.gson.annotations.SerializedName


data class YelpRestaurant (
    val Title: String,
    val Year: String,
    @SerializedName("Poster") val imageUrl: String
)


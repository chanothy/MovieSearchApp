package com.example.moviesearchapp.model

import com.google.gson.annotations.SerializedName

/**
 * Information to be parsed from JSON
 */

data class MovieInfo (
    val Title: String,
    val Year: String,
    val Rated: String,
    val Runtime: String,
    @SerializedName("imdbRating") val rating: String,
    @SerializedName("imdbID") val link: String,
    @SerializedName("Poster") val imageUrl: String
)


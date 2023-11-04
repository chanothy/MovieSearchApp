package com.example.moviesearchapp.model

import com.google.gson.annotations.SerializedName

data class YelpSearchResult(
//    @SerializedName("total") val total: Int,
//    @SerializedName("movies") val movies: List<YelpRestaurant>
    val Title: String,
    val Year: String
)

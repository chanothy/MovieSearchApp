package com.example.moviesearchapp.model

import com.google.gson.annotations.SerializedName


data class YelpRestaurant(
    val Title: String,
    val Year: String,
//    @SerializedName("rated") val rating: String,
//    @SerializedName("distance") val distanceInMeters: Double,
//    @SerializedName("image_url") val imageUrl: String,
//    val categories: List<YelpCategory>,
//    val location: YelpLocation
) {
//    fun displayDistance(): String {
//        val milesPerMeter = 0.000621371
//        val distanceInMiles = "%.2f".format(distanceInMeters * milesPerMeter)
//        return "$distanceInMiles mi"
//    }
}


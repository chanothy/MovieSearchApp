package com.example.moviesearchapp

import com.example.moviesearchapp.model.MovieInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface MovieService {

    @GET("/")
    fun searchRestaurants(
        @Query("t") searchTerm: String,
        @Query("apikey") apikey: String
    ) : Call<MovieInfo>
}
package com.example.moviesearchapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearchapp.model.YelpRestaurant
import com.example.moviesearchapp.model.YelpSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.omdbapi.com/"
private const val API_KEY = "c3830d88"
class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MoviesAdapter(this)
        val rvRestaurants = this.findViewById<RecyclerView>(R.id.rvRestaurants)
        rvRestaurants.adapter = adapter

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val movieService = retrofit.create(MovieService::class.java)
        val movieTitle = "Top Gun"

        movieService.searchRestaurants( movieTitle, "$API_KEY").enqueue(object :
            Callback<YelpSearchResult> {
            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if (body == null) {
                    Log.w(TAG, "Did not receive valid response body from OMDb API... exiting")
                    return
                }
                Log.d("test", body.toString())
//                adapter.submitList(body.movies)
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }
        })

    }
}




package com.example.moviesearchapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearchapp.model.YelpRestaurant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.moviesearchapp.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

private const val BASE_URL = "https://www.omdbapi.com/"
private const val API_KEY = "c3830d88"
class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)
        val rvRestaurants = binding.rvRestaurants

        // buttons and tvs
        val searchButton = binding.searchButton
        val searchTV = binding.movieSearchTV
        // end buttons and tvs

        // Sets up the toolbar
        val toolbar: MaterialToolbar = binding.materialToolbar
        setSupportActionBar(toolbar)

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val movieService = retrofit.create(MovieService::class.java)

        // check if there is a movie entered
        searchButton.setOnClickListener {
            if (searchTV.text.toString() != "") {
                val movieTitle = searchTV.text.toString()
                movieService.searchRestaurants( movieTitle, "$API_KEY").enqueue(object :
                    Callback<YelpRestaurant> {
                    override fun onResponse(call: Call<YelpRestaurant>, response: Response<YelpRestaurant>) {
                        Log.i(TAG, "onResponse $response")
                        val body = response.body()
                        val adapter = body?.let { MoviesAdapter(this@MainActivity, it) }
                        rvRestaurants.adapter = adapter

                        if (body == null) {
                            Log.w(TAG, "Did not receive valid response body from OMDb API... exiting")
                            return
                        }
                        Log.d("test", body.toString())
//                adapter.submitList(body.movies)
                    }

                    override fun onFailure(call: Call<YelpRestaurant>, t: Throwable) {
                        Log.i(TAG, "onFailure $t")
                    }
                })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.feedbackButton -> {
                Log.d("Feedback","button clicked")

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}




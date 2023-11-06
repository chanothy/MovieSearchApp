package com.example.moviesearchapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesearchapp.model.MovieInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.moviesearchapp.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

/**
 * MainActivity for MovieSearchApp
 *
 * Contains search functionality through Retrofit.
 * Connects to adapter that manages the recycler view for displaying search results.
 * Contains setup for toolbar.
 *
 * @author Timothy Chan
 */

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
                    Callback<MovieInfo> {
                    override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                        Log.i(TAG, "onResponse $response")
                        val body = response.body()
                        val adapter = body?.let { MoviesAdapter(this@MainActivity, it) }
                        rvRestaurants.adapter = adapter

                        if (body == null) {
                            Log.w(TAG, "Did not receive valid response body from OMDb API... exiting")
                            return
                        }
                        Log.d("test", body.toString())
                    }

                    override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                        Log.i(TAG, "onFailure $t")
                    }
                })
            }
        }
    }

    // set up toolbar
    /**
     * Creates toolbar
     * @param menu - menu item
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // send email process
    private val sendEmail =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d("Feedback","sent")
                Toast.makeText(this, "Feedback sent", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("Feedback","failed")
                Toast.makeText(this, "Feedback not sent", Toast.LENGTH_SHORT).show()
            }
        }

    // send email and set up texts
    private fun sendFeedback() {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "message/rfc822"

        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("timchan@iu.edu"))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for IMDBish")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Enter feedback here")

        val chooser = Intent.createChooser(emailIntent, "Send email using...")
        sendEmail.launch(chooser)
    }

    // toolbar selects
    /**
     * Manages toolbar buttons
     * @param item - button item in the toolbar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.feedbackButton -> {
                Log.d("Feedback","button clicked")
                sendFeedback()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}




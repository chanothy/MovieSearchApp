package com.example.moviesearchapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesearchapp.databinding.MovieItemBinding
import com.example.moviesearchapp.model.MovieInfo


class MoviesAdapter(val context: Context, private val movie: MovieInfo) :
    RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ItemViewHolder = ItemViewHolder.inflateFrom(parent)

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(movie, context)
    }

    class ItemViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }

        // assigns parsed JSON info to tvs
        fun bind(movie: MovieInfo, context: Context) {
            binding.tvName.text = movie.Title
            binding.tvIMDBrating.text = "IMDb Rating: " + movie.rating
            binding.tvPGRating.text = "- " + movie.Rated + " -"
            binding.tvYear.text = movie.Year
            binding.tvRunningTime.text = movie.Runtime
            binding.tvIMDBLink.text = "https://www.imdb.com/title/${movie.link}/"

            binding.tvIMDBLink.setOnClickListener {
                Log.d("link",binding.tvIMDBLink.text.toString())
                openLink(context,binding.tvIMDBLink.text.toString())
            }

            binding.shareButton.setOnClickListener {
                share(context)
            }

            Glide.with(context).load(movie.imageUrl).into(binding.imageView)
            Glide.with(context).load(movie.imageUrl)
                .apply(
                    RequestOptions().transform(
                        CenterCrop(), RoundedCorners(20)
                    )
                )
                .into(binding.imageView)
        }

        // opens link using intent
        fun openLink(context: Context, url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }

        // share movie with intent
        private fun share(context: Context) {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "message/rfc822"

            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "I'm sharing this movie: " + binding.tvName.text.toString())
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out: " + binding.tvIMDBLink.text.toString())

            val chooser = Intent.createChooser(emailIntent, "Send email using...")
            context.startActivity(chooser)
        }

    }
}
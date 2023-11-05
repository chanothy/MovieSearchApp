package com.example.moviesearchapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesearchapp.databinding.RestaurantItemBinding
import com.example.moviesearchapp.model.MovieInfo


class MoviesAdapter(val context: Context, private val restaurant: MovieInfo) :
    RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ItemViewHolder = ItemViewHolder.inflateFrom(parent)

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(restaurant, context)
    }


    class ItemViewHolder(val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RestaurantItemBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }

        fun bind(restaurant: MovieInfo, context: Context) {
            binding.tvName.text = restaurant.Title
            binding.tvIMDBrating.text = "IMDb Rating: " + restaurant.rating
            binding.tvPGRating.text = restaurant.Rated
            binding.tvYear.text = restaurant.Year
            binding.tvIMDBLink.text = "https://www.imdb.com/title/${restaurant.link}/"

            binding.tvIMDBLink.setOnClickListener {
                Log.d("link",binding.tvIMDBLink.text.toString())
                openLink(context,binding.tvIMDBLink.text.toString())
            }

            Glide.with(context).load(restaurant.imageUrl).into(binding.imageView)
            Glide.with(context).load(restaurant.imageUrl)
                .apply(
                    RequestOptions().transform(
                        CenterCrop(), RoundedCorners(20)
                    )
                )
                .into(binding.imageView)
        }

        fun openLink(context: Context, url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }

    }
}
package com.example.moviesearchapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearchapp.databinding.RestaurantItemBinding
import com.example.moviesearchapp.model.YelpRestaurant
import retrofit2.Callback


class MoviesAdapter(val context: Context, private val restaurant: YelpRestaurant) :
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

        fun bind(restaurant: YelpRestaurant, context: Context) {
            binding.tvName.text = restaurant.Title
            Log.d("title",restaurant.Title)
//            binding.ratingBar.rating = restaurant.rating
//            binding.tvNumReviews.text = "${restaurant.numReviews} Reviews"
            binding.tvAddress.text = restaurant.Year
//            binding.tvCategory.text = restaurant.rating
//            binding.tvDistance.text = restaurant.displayDistance()
//            binding.tvPrice.text = restaurant.price
//            Glide.with(context).load(restaurant.imageUrl).into(binding.imageView)

//            Glide.with(context).load(restaurant.imageUrl)
//                .apply(
//                    RequestOptions().transform(
//                        CenterCrop(), RoundedCorners(20)
//                    )
//                )
//                .into(binding.imageView)


        }


    }
}
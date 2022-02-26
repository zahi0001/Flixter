package com.codepath.flixter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "MovieAdapter"
class MovieAdapter(private val context: Context,private val movies: List<Movie>)
    :RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    //Expensive Operation: create a view ?????? I am not sure about this one what is bind???
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "OnCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    //Cheap: simply bind data to an existing view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "OnBindViewHolder position $position")
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        fun bind (movie: Movie){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
        }
    }
}

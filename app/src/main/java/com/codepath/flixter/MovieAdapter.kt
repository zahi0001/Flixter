package com.codepath.flixter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind (movie: Movie){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
        }

        override fun onClick(p0: View?) {
            // 1. Get notified of the particular movie which was clicked
            val movie = movies[adapterPosition]
            //Useful to debug: Toast.makeText(context,movie.title, Toast.LENGTH_SHORT).show()
            // 2. Use the intent system to navigate to the new activity
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}

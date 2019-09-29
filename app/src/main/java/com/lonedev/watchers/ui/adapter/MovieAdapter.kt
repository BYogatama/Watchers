package com.lonedev.watchers.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lonedev.watchers.R
import com.lonedev.watchers.data.model.MovieResult
import com.lonedev.watchers.databinding.ItemMovieBinding

class MovieAdapter constructor(private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movies: MutableList<MovieResult> = mutableListOf()

    lateinit var onItemClickListener: (MovieResult) -> Unit
    lateinit var onItemFavorited: (MovieResult) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate(
            inflater, R.layout.item_movie, parent, false
        ) as ItemMovieBinding
        return ViewHolder(binding, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return this.movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.create(this.movies[position])
    }

    fun add(movie: MovieResult) {
        if (!movies.contains(movie)) {
            this.movies.add(movie)
            notifyItemInserted(movies.size - 1)
        }
    }

    fun addAll(movies: List<MovieResult>) {
        for (movie in movies) {
            add(movie)
        }
    }

    fun setFavourite(movie: MovieResult) {
        onItemFavorited(movie)
    }


    class ViewHolder constructor(
        private val binding: ItemMovieBinding,
        private val onItemClickListener: (MovieResult) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun create(movie: MovieResult) {
            binding.movie = movie
            binding.executePendingBindings()

            itemView.setOnClickListener {
                onItemClickListener(movie)
            }
        }

    }
}
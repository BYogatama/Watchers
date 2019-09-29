package com.lonedev.watchers.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lonedev.watchers.BR
import com.lonedev.watchers.R
import com.lonedev.watchers.base.BaseActivity
import com.lonedev.watchers.data.model.MovieResult
import com.lonedev.watchers.databinding.ActivityDetailBinding
import com.lonedev.watchers.ui.adapter.VideoAdapter
import com.lonedev.watchers.utils.Constant

class DetailMovieActivity : BaseActivity<ActivityDetailBinding>() {

    lateinit var viewModel: DetailMovieViewModel

    private lateinit var movie: MovieResult
    private lateinit var videoAdapter : VideoAdapter

    override fun setLayoutRes(): Int {
        return R.layout.activity_detail
    }


    companion object {
        fun generateIntent(context: Context, movie: MovieResult): Intent {
            return Intent(context, DetailMovieActivity::class.java).apply {
                putExtra("movie", movie)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, factory).get(DetailMovieViewModel::class.java)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extraIntent = this.intent
        movie = extraIntent.getParcelableExtra("movie") as MovieResult

        binding.movie = movie
        binding.handler = this


        videoAdapter = VideoAdapter(this)
        binding.movieTrailer.adapter = videoAdapter
        binding.movieTrailer.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        videoAdapter.onItemClickListener = { video ->
            val openVideo =
                Intent(Intent.ACTION_VIEW, Uri.parse("${Constant.VIDEO_URL}${video.key}"))
            startActivity(openVideo)
        }

        observeOnFavourites()
        observeVideos()

    }

    fun setFavourite(movie: MovieResult) {
        movie.isFavourite = !movie.isFavourite
        if (movie.isFavourite) {
            viewModel.saveFavourites(movie)
        } else {
            viewModel.deleteFavourite(movie)
        }
    }

    private fun observeOnFavourites() {
        viewModel.favourite.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            binding.movie!!.isFavourite = !binding.movie!!.isFavourite
            binding.notifyPropertyChanged(BR.movie)
        })
    }

    private fun observeVideos() {
        viewModel.getVideos(movie)
        viewModel.videosMovies.observe(this, Observer {
            videoAdapter.videos.addAll(it)
            videoAdapter.notifyDataSetChanged()
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
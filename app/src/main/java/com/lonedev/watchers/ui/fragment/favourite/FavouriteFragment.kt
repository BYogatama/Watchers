package com.lonedev.watchers.ui.fragment.favourite

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.lonedev.watchers.R
import com.lonedev.watchers.base.BaseFragment
import com.lonedev.watchers.databinding.FragmentMovieBinding
import com.lonedev.watchers.ui.adapter.MovieAdapter
import com.lonedev.watchers.ui.fragment.DetailMovieActivity

class FavouriteFragment : BaseFragment<FragmentMovieBinding>() {

    private lateinit var favouriteMovieAdapter: MovieAdapter
    private lateinit var viewModel: FavouriteViewModel

    override fun setLayoutRes(): Int {
        return R.layout.fragment_movie
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, factory).get(FavouriteViewModel::class.java)

        this.favouriteMovieAdapter = MovieAdapter(getBaseActivity())
        this.favouriteMovieAdapter.onItemClickListener = { movie ->
            startActivity(DetailMovieActivity.generateIntent(getBaseActivity(), movie))
        }

        this.binding.recyclerView.layoutManager = GridLayoutManager(getBaseActivity(), 2)
        this.binding.recyclerView.adapter = this.favouriteMovieAdapter

        viewModel.getFavouriteMovies()
        viewModel.favouriteMovies.observe(this, Observer {
            favouriteMovieAdapter.addAll(it)
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavouriteMovies()
    }

}
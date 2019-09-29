package com.lonedev.watchers.ui.fragment.toprated

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.lonedev.watchers.R
import com.lonedev.watchers.base.BaseFragment
import com.lonedev.watchers.databinding.FragmentMovieBinding
import com.lonedev.watchers.ui.adapter.MovieAdapter
import com.lonedev.watchers.ui.fragment.DetailMovieActivity

class TopRatedFragment : BaseFragment<FragmentMovieBinding>() {

    lateinit var topRatedMoviesAdapter: MovieAdapter

    private lateinit var viewModel: TopRatedViewModel

    override fun setLayoutRes(): Int {
        return R.layout.fragment_movie
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, factory).get(TopRatedViewModel::class.java)

        this.topRatedMoviesAdapter = MovieAdapter(getBaseActivity())
        this.topRatedMoviesAdapter.onItemClickListener = { movie ->
            startActivity(DetailMovieActivity.generateIntent(getBaseActivity(), movie))
        }

        this.binding.recyclerView.layoutManager = GridLayoutManager(getBaseActivity(), 2)
        this.binding.recyclerView.adapter = this.topRatedMoviesAdapter

        viewModel.getTopRatedMovies(1)
        viewModel.topRatedMovies.observe(this, Observer {
            topRatedMoviesAdapter.add(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTopRatedMovies(1)
    }
}
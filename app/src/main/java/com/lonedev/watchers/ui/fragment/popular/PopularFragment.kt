package com.lonedev.watchers.ui.fragment.popular

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.lonedev.watchers.R
import com.lonedev.watchers.base.BaseFragment
import com.lonedev.watchers.databinding.FragmentMovieBinding
import com.lonedev.watchers.ui.adapter.MovieAdapter
import com.lonedev.watchers.ui.fragment.DetailMovieActivity

class PopularFragment : BaseFragment<FragmentMovieBinding>() {


    lateinit var popularMovieAdapter: MovieAdapter

    private lateinit var viewModel: PopularViewModel

    override fun setLayoutRes(): Int {
        return R.layout.fragment_movie
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, factory).get(PopularViewModel::class.java)

        this.popularMovieAdapter = MovieAdapter(getBaseActivity())
        this.popularMovieAdapter.onItemClickListener = { movie ->
            startActivity(DetailMovieActivity.generateIntent(getBaseActivity(), movie))
        }

        this.binding.recyclerView.layoutManager = GridLayoutManager(getBaseActivity(), 2)
        this.binding.recyclerView.adapter = this.popularMovieAdapter

        viewModel.getPopularMovies(1)
        viewModel.popularMovies.observe(this, Observer {
            popularMovieAdapter.add(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPopularMovies(1)
    }

}
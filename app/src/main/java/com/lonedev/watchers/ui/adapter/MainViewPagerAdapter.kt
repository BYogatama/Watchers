package com.lonedev.watchers.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lonedev.watchers.R
import com.lonedev.watchers.ui.fragment.favourite.FavouriteFragment
import com.lonedev.watchers.ui.fragment.popular.PopularFragment
import com.lonedev.watchers.ui.fragment.toprated.TopRatedFragment

class MainViewPagerAdapter
constructor(
    private val context: Context,
    fm: FragmentManager, behavior: Int
) : FragmentPagerAdapter(fm, behavior) {

    companion object {
        const val MOVIES = 0
        const val SERIES = 1
        const val FAVOURITE = 2
    }

    private val fragments = listOf(MOVIES, SERIES, FAVOURITE)

    override fun getItem(position: Int): Fragment {
        return when (fragments[position]) {
            MOVIES -> PopularFragment()
            SERIES -> TopRatedFragment()
            FAVOURITE -> FavouriteFragment()
            else -> PopularFragment()
        }
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (fragments[position]) {
            MOVIES -> context.getString(R.string.tab_popular)
            SERIES -> context.getString(R.string.tab_top_rated)
            FAVOURITE -> "Favourite"
            else -> "No Title"
        }
    }

}
package com.lonedev.watchers.ui

import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lonedev.watchers.base.BaseViewModel
import com.lonedev.watchers.ui.adapter.MainViewPagerAdapter
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        @BindingAdapter("handler")
        @JvmStatic
        fun bindViewPagerAdapter(viewPager: ViewPager, activity: MainActivity) {
            val viewPagerAdapter = MainViewPagerAdapter(
                activity, activity.supportFragmentManager,
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
            viewPager.adapter = viewPagerAdapter
        }

        @BindingAdapter("pager")
        @JvmStatic
        fun bindViewPagerTabs(view: TabLayout, viewPager: ViewPager) {
            view.setupWithViewPager(viewPager, true)
        }
    }

}
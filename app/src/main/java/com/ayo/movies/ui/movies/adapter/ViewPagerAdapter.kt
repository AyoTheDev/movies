package com.ayo.movies.ui.movies.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ayo.movies.R
import com.ayo.movies.ui.movies.fragment.MoviesFragment

class ViewPagerAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    companion object {
        private const val PAGE_COUNT = 2
    }

    override fun getItem(position: Int): Fragment {
        return MoviesFragment.newInstance(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) context.getString(R.string.popular)
        else context.getString(R.string.favourite)
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }
}
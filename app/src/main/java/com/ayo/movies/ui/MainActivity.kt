package com.ayo.movies.ui

import android.os.Bundle
import com.ayo.movies.R
import com.ayo.movies.ui.adapter.ViewPagerAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    private val viewPagerAdapter by lazy { ViewPagerAdapter(this, supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        viewpager?.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewpager)
    }
}

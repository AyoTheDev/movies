package com.ayo.movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayo.movies.R
import com.ayo.movies.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewPagerAdapter by lazy { ViewPagerAdapter(this, supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView(){
        viewpager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewpager)
    }
}

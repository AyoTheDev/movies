package com.ayo.movies.ui.movies.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ayo.movies.R
import com.ayo.movies.di.ViewModelFactory
import com.ayo.movies.ui.movies.viewmodel.MainViewModel
import com.ayo.movies.ui.movies.adapter.ViewPagerAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private val viewPagerAdapter by lazy { ViewPagerAdapter(this, supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        loadData()
    }

    private fun loadData(){
        viewModel.apply {
            loadPopularMovies()
            loadFavouriteMovies()
        }
    }

    private fun setUpView() {
        viewpager?.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewpager)
    }
}

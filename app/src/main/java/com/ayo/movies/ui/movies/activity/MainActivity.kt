package com.ayo.movies.ui.movies.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ayo.movies.databinding.ActivityMainBinding
import com.ayo.movies.di.ViewModelFactory
import com.ayo.movies.ui.movies.viewmodel.MainViewModel
import com.ayo.movies.ui.movies.adapter.ViewPagerAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: ActivityMainBinding


    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private val viewPagerAdapter by lazy { ViewPagerAdapter(this, supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.viewpager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewpager)
    }
}

package com.ayo.movies.ui.breakingbadcampustours.activity

import android.app.ActionBar
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayo.movies.R
import com.ayo.movies.databinding.ActivityMainBinding
import com.ayo.movies.di.ViewModelFactory
import com.ayo.movies.ui.breakingbadcampustours.adapter.CampusListAdapter
import com.ayo.movies.ui.breakingbadcampustours.viewmodel.MainViewModel
import com.ayo.movies.utils.Resource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: ActivityMainBinding

    private val adapter: CampusListAdapter by lazy { CampusListAdapter() }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.title_bar)
        }

        setContentView(binding.root)
        setUpView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.campusLiveData.observe(this, { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.update(resource.data)
                    isLoading(false)
                }
                is Resource.Loading -> isLoading(resource.loading)
                is Resource.Failure -> {
                    //todo show a snack bar with the error
                }
            }
        })
    }


    private fun isLoading(isLoading: Boolean) {
        binding.loading.visibility =
            if (isLoading) View.VISIBLE else View.INVISIBLE
    }

    private fun setUpView() {
        binding.campusList.adapter = adapter
        binding.campusList.layoutManager = LinearLayoutManager(this)

    }
}

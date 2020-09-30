package com.ayo.movies.ui.movies.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayo.movies.databinding.ActivityMainBinding
import com.ayo.movies.di.ViewModelFactory
import com.ayo.movies.ui.movies.adapter.UserListAdapter
import com.ayo.movies.ui.movies.listeners.SearchQueryListener
import com.ayo.movies.ui.movies.viewmodel.MainViewModel
import com.ayo.movies.utils.Resource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: ActivityMainBinding

    private val adapter: UserListAdapter by lazy {
        UserListAdapter(userListener)
    }


    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        setUpListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.usersLiveData.observe(this, { resource ->
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


    private val userListener = object : UserListAdapter.Listener {
        override fun onClick(position: Int) {
            val user = adapter.getItem(position)
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    private fun setUpListeners() {
        binding.searchBox.addTextChangedListener(searchQueryListener)
        binding.searchButton.setOnClickListener {
            val queryText = binding.searchBox.text.toString()
            searchQueryListener.onSearchButtonClicked(queryText)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.loading.visibility =
            if (isLoading) View.VISIBLE else View.INVISIBLE
    }

    private val searchQueryListener =
        SearchQueryListener(this.lifecycle,
            { queryText -> queryText.let { if (it.isNotBlank()) viewModel.loadUsers(it) } },
            { viewModel.loadUsers() })


    private fun setUpView() {
        binding.userList.adapter = adapter
        binding.userList.layoutManager = LinearLayoutManager(this)
    }
}

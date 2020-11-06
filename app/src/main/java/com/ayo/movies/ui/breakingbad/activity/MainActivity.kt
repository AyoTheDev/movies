package com.ayo.movies.ui.breakingbad.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayo.movies.R
import com.ayo.movies.databinding.ActivityMainBinding
import com.ayo.movies.di.ViewModelFactory
import com.ayo.movies.ui.breakingbad.adapter.CharacterListAdapter
import com.ayo.movies.ui.breakingbad.listeners.SearchQueryListener
import com.ayo.movies.ui.breakingbad.viewmodel.MainViewModel
import com.ayo.movies.utils.Resource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: ActivityMainBinding

    private val adapter: CharacterListAdapter by lazy {
        CharacterListAdapter(characterListener)
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
        viewModel.charactersLiveData.observe(this, { resource ->
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


    private val characterListener = object : CharacterListAdapter.Listener {
        override fun onClick(position: Int) {
            val character = adapter.getItem(position)
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("character", character)
            startActivity(intent)
        }
    }

    private fun setUpListeners() {
        binding.searchBox.addTextChangedListener(searchQueryListener)
        binding.searchButton.setOnClickListener {
            val queryText = binding.searchBox.text.toString()
            searchQueryListener.onSearchButtonClicked(queryText)
        }
        binding.seasonSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.loading.visibility =
            if (isLoading) View.VISIBLE else View.INVISIBLE
    }

    private val searchQueryListener =
        SearchQueryListener(this.lifecycle,
            { queryText -> queryText.let { if (it.isNotBlank()) viewModel.loadCharacters(it) } },
            { viewModel.loadCharacters() })


    private fun setUpView() {
        binding.userList.adapter = adapter
        binding.userList.layoutManager = LinearLayoutManager(this)

        //set up season select spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.season_options,
            android.R.layout.simple_spinner_item
        )

        binding.seasonSelector.adapter = adapter

    }
}

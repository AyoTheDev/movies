package com.ayo.movies.ui.breakingbad.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayo.movies.R
import com.ayo.movies.databinding.ActivityDetailsBinding
import com.ayo.movies.utils.ImageLoaderUtils
import com.ayo.domain.model.CharacterDomain
import com.ayo.movies.utils.formatAppearanceList
import com.ayo.movies.utils.formatOccupationList

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        val character: CharacterDomain =
            intent?.extras?.getParcelable("character") ?: throw IllegalStateException("character is null")

        supportActionBar?.title = character.name
        binding.apply {
            ImageLoaderUtils.loadImage(this@DetailsActivity, character.imageUrl, characterImage)
            displayName.text = String.format(getString(R.string.name), character.name)
            occupation.text = String.format(getString(R.string.occupation), character.occupation?.formatOccupationList())
            status.text = String.format(getString(R.string.status), character.status)
            seasonAppearance.text = String.format(getString(R.string.season_appearance), character.appearance?.formatAppearanceList())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

package com.ayo.movies.ui.movies.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayo.domain.model.UserDomain
import com.ayo.movies.R
import com.ayo.movies.databinding.ActivityDetailsBinding
import com.ayo.movies.utils.ImageLoaderUtils
import org.joda.time.DateTime

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
        val user: UserDomain =
            intent?.extras?.getParcelable("user") ?: throw IllegalStateException("user is null")

        supportActionBar?.title = user.displayName
        binding.apply {
            ImageLoaderUtils.loadImage(applicationContext, user.profileImageURL, profileImage)
            displayName.text = String.format(getString(R.string.user_name), user.displayName.trim())
            reputation.text = String.format(getString(R.string.reputation), user.reputation)
            badges.text =
                String.format(
                    getString(R.string.badges),
                    user.goldBadges, user.silverBadges, user.bronzeBadges
                )
            user.location?.let {
                location.text = String.format(getString(R.string.location), it)
            }
            user.age?.let {
                age.text = String.format(getString(R.string.age), it)
            }

            val date = DateTime(user.creationDate.toLong() * 1000)
            val creationDateText = "${date.dayOfMonth}-${date.monthOfYear}-${date.year}"
            creationDate.text = String.format(getString(R.string.creation_date), creationDateText)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

package com.example.mydicodingfundamentalsubmission.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mydicodingfundamentalsubmission.R
import com.example.mydicodingfundamentalsubmission.data.response.DetailUserResponse
import com.example.mydicodingfundamentalsubmission.database.favoriteUser
import com.example.mydicodingfundamentalsubmission.databinding.ActivityDetailBinding
import com.example.mydicodingfundamentalsubmission.ui.follow.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var username: String? = null

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_label_1,
            R.string.tab_label_2
        )
    }

    private lateinit var favoriteAddUpdateViewModel: FavoriteAddUpdateViewModel

    private var _activityFavoriteAddUpdateBinding: ActivityDetailBinding? = null
    private val favoriteBinding get() = _activityFavoriteAddUpdateBinding
    private var isFavoriteLiveData: LiveData<List<favoriteUser>>? = null
    private lateinit var favoritesUsernames: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteAddUpdateViewModel = ViewModelProvider(this).get(FavoriteAddUpdateViewModel::class.java)


        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        val username = intent.getStringExtra("username")
        val avatarUrl = intent.getStringExtra("avatarUrl")
        detailViewModel.detail.observe(this){detail ->
            setDetailData(detail)
        }

        isFavoriteLiveData = favoriteAddUpdateViewModel.getAllFavorite()
        isFavoriteLiveData!!.observe(this, Observer<List<favoriteUser>> { favoriteList ->
            favoritesUsernames = favoriteList.map { it.username }
            updateFavoriteIcon(username)
        })

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }


        if (username != null) {
            detailViewModel.getUserDetail(username)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = username ?: ""
        val viewPager : ViewPager2 = findViewById(R.id.view_pager)
        binding.viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(binding.tabs, binding.viewPager) {
                tab, position -> tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        binding.floatingActionButton.setOnClickListener{
            addToFavorite(username, avatarUrl)
        }
    }

    private fun setDetailData(detail: DetailUserResponse?) {
        detail?.let {
            binding.tvUsername.text = it.login
            binding.tvNama.text = it.name
            binding.tvFollowers.text = "Followers ${it.followers.toString()}"
            binding.tvFollowing.text = "Following ${it.following.toString()}"

            Glide.with(this)
                .load(it.avatarUrl)
                .circleCrop()
                .into(binding.profileImage)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun addToFavorite(username: String?, avatarUrl: String?) {
        username ?: return
        avatarUrl ?: return
        val user = favoriteUser(username, avatarUrl)

        // Check if the username is already in the favorites list
        if (favoritesUsernames.contains(username)) {
            favoriteAddUpdateViewModel.delete(user)
        } else {
            favoriteAddUpdateViewModel.insert(user)
        }
    }

    private fun updateFavoriteIcon(username: String?) {
        val isFavorite = favoritesUsernames.contains(username)
        val drawableResId = if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
        binding.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(this, drawableResId))
    }





}
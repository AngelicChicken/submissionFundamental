package com.example.mydicodingfundamentalsubmission.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mydicodingfundamentalsubmission.R
import com.example.mydicodingfundamentalsubmission.data.response.DetailUserResponse
import com.example.mydicodingfundamentalsubmission.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_label_1,
            R.string.tab_label_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        val username = intent.getStringExtra("username")
        detailViewModel.detail.observe(this){detail ->
            setDetailData(detail)
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
    }

    private fun setDetailData(detail: DetailUserResponse?) {
        // Update UI with user detail data
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
}
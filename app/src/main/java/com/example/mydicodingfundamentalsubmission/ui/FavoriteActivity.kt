package com.example.mydicodingfundamentalsubmission.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydicodingfundamentalsubmission.databinding.ActivityFavoriteBinding
import com.example.mydicodingsubmissionawal.ui.UserAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var viewModel: FavoriteAddUpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter()
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = userAdapter
        }

        viewModel = ViewModelProvider(this).get(FavoriteAddUpdateViewModel::class.java)
        viewModel.showAllFavorite().observe(this) { favoriteUsers ->
            userAdapter.submitList(favoriteUsers)
        }
    }
}
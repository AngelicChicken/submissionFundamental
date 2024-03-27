package com.example.mydicodingfundamentalsubmission.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydicodingfundamentalsubmission.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteUserAdapter: FavoriteUserAdapter
    private lateinit var viewModel: FavoriteAddUpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteUserAdapter = FavoriteUserAdapter(emptyList()) // Initialize with empty list
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = favoriteUserAdapter
        }

        viewModel = ViewModelProvider(this).get(FavoriteAddUpdateViewModel::class.java)
        viewModel.getAllFavorite().observe(this) { favoriteUsers ->
            favoriteUserAdapter.userList = favoriteUsers // Update the list in the adapter
            favoriteUserAdapter.notifyDataSetChanged() // Notify adapter about the data change
        }
    }
}
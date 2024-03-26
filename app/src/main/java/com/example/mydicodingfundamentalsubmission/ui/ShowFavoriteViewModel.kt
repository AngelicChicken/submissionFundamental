package com.example.mydicodingfundamentalsubmission.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mydicodingfundamentalsubmission.database.favoriteUser
import com.example.mydicodingfundamentalsubmission.repository.FavoriteRepository

class ShowFavoriteViewModel(application: Application) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorite(): LiveData<List<favoriteUser>> = mFavoriteRepository.getAllFavorite()
}
package com.example.mydicodingfundamentalsubmission.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.mydicodingfundamentalsubmission.database.favoriteUser
import com.example.mydicodingfundamentalsubmission.repository.FavoriteRepository

class FavoriteAddUpdateViewModel(application: Application): ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(user: favoriteUser){
        mFavoriteRepository.insert(user)
    }

    fun update(user: favoriteUser){
        mFavoriteRepository.update(user)
    }

    fun delete(user: favoriteUser){
        mFavoriteRepository.delete(user)
    }
}
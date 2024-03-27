package com.example.mydicodingfundamentalsubmission.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.mydicodingfundamentalsubmission.data.response.User
import com.example.mydicodingfundamentalsubmission.database.favoriteUser
import com.example.mydicodingfundamentalsubmission.repository.FavoriteRepository

class ShowFavoriteViewModel(application: Application) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorite(): LiveData<List<User>> {
        return mFavoriteRepository.getAllFavorite().map { favoriteUsers ->
            favoriteUsers.map { favoriteUser ->
                User(favoriteUser.username, favoriteUser.avatarUrl)
            }
        }
    }

    fun getFavoriteByUsername(username: String): favoriteUser? {
        return mFavoriteRepository.getFavoriteByUsername(username)
    }

}
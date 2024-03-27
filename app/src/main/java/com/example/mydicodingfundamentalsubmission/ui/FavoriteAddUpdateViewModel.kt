package com.example.mydicodingfundamentalsubmission.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.mydicodingfundamentalsubmission.data.response.User
import com.example.mydicodingfundamentalsubmission.database.favoriteUser
import com.example.mydicodingfundamentalsubmission.repository.FavoriteRepository

class FavoriteAddUpdateViewModel(application: Application): AndroidViewModel(application) {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(user: favoriteUser){
        mFavoriteRepository.insert(user)
    }

    fun update(user: favoriteUser){
        mFavoriteRepository.update(user)
    }

    // Expose getAllFavorite function
    fun getAllFavorite(): LiveData<List<favoriteUser>> {
        return mFavoriteRepository.getAllFavorite()
    }

    fun showAllFavorite(): LiveData<List<User>> {
        return mFavoriteRepository.getAllFavorite().map { favoriteUsers ->
            favoriteUsers.map { favoriteUser ->
                User(favoriteUser.username, favoriteUser.avatarUrl)
            }
        }
    }

    fun delete(user: favoriteUser){
        mFavoriteRepository.delete(user)
    }
}
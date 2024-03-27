package com.example.mydicodingfundamentalsubmission.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mydicodingfundamentalsubmission.database.FavoriteDao
import com.example.mydicodingfundamentalsubmission.database.FavoriteUserDatabase
import com.example.mydicodingfundamentalsubmission.database.favoriteUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init{
        val db = FavoriteUserDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorite(): LiveData<List<favoriteUser>> = mFavoriteDao.getAllFavorite()

    fun insert(user: favoriteUser){
        executorService.execute { mFavoriteDao.insert(user) }
    }

    fun delete(user: favoriteUser){
        executorService.execute { mFavoriteDao.delete(user) }
    }

    fun update(user: favoriteUser){
        executorService.execute { mFavoriteDao.update(user) }
    }

    fun getFavoriteByUsername(user: String): favoriteUser? {
        return mFavoriteDao.getFavoriteByUsername(user)
    }
}
package com.example.mydicodingfundamentalsubmission.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: favoriteUser)

    @Update
    fun update(user: favoriteUser)

    @Delete
    fun delete(user: favoriteUser)

    @Query("SELECT * from favoriteUser ORDER BY username ASC")
    fun getAllFavorite(): LiveData<List<favoriteUser>>
}
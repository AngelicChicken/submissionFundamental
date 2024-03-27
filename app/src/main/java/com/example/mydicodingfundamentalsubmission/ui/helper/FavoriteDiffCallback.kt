package com.example.mydicodingfundamentalsubmission.ui.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.mydicodingfundamentalsubmission.database.favoriteUser

class FavoriteDiffCallback(private val oldFavoriteList: List<favoriteUser>, private val newFavoriteList: List<favoriteUser>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFavoriteList.size
    override fun getNewListSize(): Int = newFavoriteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFavoriteList[oldItemPosition].username == newFavoriteList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = oldFavoriteList[oldItemPosition]
        val newFavorite = newFavoriteList[newItemPosition]
        return oldFavorite.username == newFavorite.username && oldFavorite.avatarUrl == newFavorite.avatarUrl
    }
}
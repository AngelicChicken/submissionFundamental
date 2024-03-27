package com.example.mydicodingfundamentalsubmission.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory{
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowFavoriteViewModel::class.java)){
            return ShowFavoriteViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(FavoriteAddUpdateViewModel::class.java)){
            return FavoriteAddUpdateViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown viewModel class: ${modelClass.name}")
    }
}
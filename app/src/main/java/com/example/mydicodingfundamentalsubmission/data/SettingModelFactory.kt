package com.example.mydicodingfundamentalsubmission.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SettingModelFactory(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(settingViewModel::class.java)){
            return settingViewModel(pref) as T
        }
        throw  IllegalArgumentException("unknown ViewModel class: "+ modelClass.name)
    }
}
package com.example.mydicodingfundamentalsubmission.ui.follow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydicodingfundamentalsubmission.data.response.User
import com.example.mydicodingfundamentalsubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class followersViewModel : ViewModel() {
    private val _followers = MutableLiveData<List<User?>>()
    val followers : LiveData<List<User?>> = _followers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "FollowersViewModel"
    }

    fun findFollowers(input: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(input)
        client.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val followers: List<User>? = response.body()
                    _followers.value = followers
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }}
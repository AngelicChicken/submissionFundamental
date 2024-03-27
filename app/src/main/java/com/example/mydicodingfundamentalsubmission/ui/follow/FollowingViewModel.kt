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

class followingViewModel : ViewModel() {
    private val _following = MutableLiveData<List<User?>>()
    val following : LiveData<List<User?>> = _following

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "FollowingViewModel"
    }

    fun findFollowing(input: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(input)
        client.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val following: List<User>? = response.body()
                    _following.value = following
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }}
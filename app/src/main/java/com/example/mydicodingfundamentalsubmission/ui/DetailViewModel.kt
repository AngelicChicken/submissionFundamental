package com.example.mydicodingfundamentalsubmission.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydicodingfundamentalsubmission.data.response.DetailUserResponse
import com.example.mydicodingfundamentalsubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _userDetail = MutableLiveData<DetailUserResponse?>()
    val detail: LiveData<DetailUserResponse?> = _userDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "DetailViewModel"
    }

    fun getUserDetail(input: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(input)
        client.enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse?>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val userDetail: DetailUserResponse? = response.body()
                    _userDetail.value = userDetail
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
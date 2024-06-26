package com.example.mydicodingfundamentalsubmission.data.retrofit

import com.example.mydicodingfundamentalsubmission.data.response.DetailUserResponse
import com.example.mydicodingfundamentalsubmission.data.response.User
import com.example.mydicodingfundamentalsubmission.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("q") username:String
    ): Call<UserResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username")
        username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username")
        username: String
    ): Call<List<User>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username")
        username: String
    ): Call<List<User>>
}
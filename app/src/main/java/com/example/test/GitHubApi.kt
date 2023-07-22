package com.example.test

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<SearchResponse>
    @GET("users/{username}/repos")
    fun getUserRepositories(@Path("username") username: String): Call<List<Info>>
}
data class SearchResponse(
    val items: List<User>
)


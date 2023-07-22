package com.example.test

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage
    val users: LiveData<List<User>> get() = _users
    fun searchUsers(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val gitHubApi = retrofit.create(GitHubApi::class.java)
        val call = gitHubApi.searchUsers(query)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    val searchResponse = response.body()
                    val users = searchResponse?.items ?: emptyList()
                    _users.value = users
                    if(users.isEmpty())
                    {
                        _toastMessage.value = "沒有這個使用者"
                    }
                } else {
                    // 處理請求失敗
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                // 處理請求失敗
            }
        })
    }
}

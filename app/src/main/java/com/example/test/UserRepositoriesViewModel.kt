package com.example.test

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepositoriesViewModel : ViewModel() {
    private val _repositories = MutableLiveData<List<Info>>()

    val repositories: LiveData<List<Info>> get() = _repositories
    fun getUserRepositories(username: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val gitHubApi = retrofit.create(GitHubApi::class.java)
        val call = gitHubApi.getUserRepositories(username)
        call.enqueue(object : Callback<List<Info>> {
            override fun onResponse(call: Call<List<Info>>, response: Response<List<Info>>) {
                if (response.isSuccessful) {
                    //val userRepositories = response.body()
                    //Log.i("TAG", "isSuccessful")
                    _repositories.value = response.body()!!

                } else {
                    // 处理请求失败
                }
            }

            override fun onFailure(call: Call<List<Info>>, t: Throwable) {

            }
        })
    }
}

package com.example.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.test.databinding.ActivityMainBinding
import com.example.test.databinding.ActivitySecondBinding


class second : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var adapter: Adapter2
    private lateinit var viewModel: UserRepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val login = intent.getStringExtra("login")
        val img = intent.getStringExtra("img")
        binding.textView.text = login
        Glide.with(this)
            .load(img)
            .circleCrop()
            .into(binding.roundImageView)
        viewModel = ViewModelProvider(this).get(UserRepositoriesViewModel::class.java)

        binding.recyclerId2.layoutManager = LinearLayoutManager(this)
        adapter = Adapter2(emptyList()) // 初始時資料為空
        binding.recyclerId2.adapter = adapter
        viewModel.getUserRepositories(login!!)
        viewModel.repositories.observe(this) { repositories ->
            adapter.setData(repositories)
            //Toast.makeText(this, "Repositories loaded successfully!", Toast.LENGTH_SHORT).show()
        }

    }
}





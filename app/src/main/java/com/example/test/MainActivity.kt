package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerId.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(emptyList()) // 初始時資料為空

        adapter.setListener(object : Adapter.OnItemClickListener {
            override fun onItemClick(username: String, avator_url: String) {
                val intent = Intent(this@MainActivity, second::class.java)
                intent.putExtra("login", username)
                intent.putExtra("img", avator_url)
                startActivity(intent)
            }
        })

        binding.recyclerId.adapter = adapter

        binding.buttonId.setOnClickListener {
            val query = binding.editId.text.toString()
            viewModel.searchUsers(query)
        }

        viewModel.users.observe(this) { users ->
            adapter.setData(users)
        }

        viewModel.toastMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}

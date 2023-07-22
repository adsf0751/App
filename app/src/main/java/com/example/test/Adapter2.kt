package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ItemBinding
import com.example.test.databinding.RepoBinding
class Adapter2(private var infoList: List<Info>) : RecyclerView.Adapter<Adapter2.ViewHolder>() {
    fun setData(infoList: List<Info>) {
        this.infoList = infoList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(info: Info) {
            binding.titleTextView.text = info.name
            binding.contentTextView.text = info.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = infoList[position]
        holder.bind(info)
    }

    override fun getItemCount(): Int {
        return infoList.size
    }
}

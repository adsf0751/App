package com.example.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.ItemBinding

class Adapter(private var myData: List<User>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(username: String,avator_url:String)
    }

    private var clickListener: OnItemClickListener? = null

    fun setListener(listener: OnItemClickListener?) {
        this.clickListener = listener
    }
    fun setData(data: List<User>) {
        myData = data
        notifyDataSetChanged()
    }



    // 建立 ViewHolder
    inner class ViewHolder(private  val binding: ItemBinding,private val listener: OnItemClickListener?) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.txtItem.text = user.login
            Glide.with(itemView)
                .load(user.avatar_url)
                .into(binding.imgItem)
            binding.root.setOnClickListener {
                listener?.onItemClick(user.login,user.avatar_url)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding,clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = myData[position]
        holder.bind(user)


    }

    override fun getItemCount(): Int {
        return myData.size
    }
}

package com.example.mydicodingsubmissionawal.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mydicodingfundamentalsubmission.data.response.User
import com.example.mydicodingfundamentalsubmission.databinding.ItemUserBinding
import com.example.mydicodingfundamentalsubmission.ui.DetailActivity

class UserAdapter : ListAdapter<User, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User){
            Glide.with(binding.profileImage.context)
                .load(item.avatarUrl)
                .circleCrop()
                .into(binding.profileImage)

            binding.tvUsername.text = item.login

            binding.root.setOnClickListener{
                val context = binding.root.context
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("username", item.login)
                intent.putExtra("avatarUrl", item.avatarUrl)
                context.startActivity(intent)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val userInfo = getItem(position)
        holder.bind(userInfo)
    }

    companion object{
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }


}



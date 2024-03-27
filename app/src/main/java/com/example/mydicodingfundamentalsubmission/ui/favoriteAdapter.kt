package com.example.mydicodingfundamentalsubmission.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mydicodingfundamentalsubmission.database.favoriteUser
import com.example.mydicodingfundamentalsubmission.databinding.ItemUserBinding

class FavoriteUserAdapter(var userList: List<favoriteUser>) : RecyclerView.Adapter<FavoriteUserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: favoriteUser) {
            binding.apply {
                Glide.with(binding.profileImage.context)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(binding.profileImage)

                tvUsername.text = user.username

                root.setOnClickListener{
                    val context = binding.root.context
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("username", user.username)
                    intent.putExtra("avatarUrl", user.avatarUrl)
                    context.startActivity(intent)
            }
        }
    }
}}

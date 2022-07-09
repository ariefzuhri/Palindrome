package com.ariefzuhri.suitmediainterntest.common.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ariefzuhri.suitmediainterntest.common.util.loadAvatar
import com.ariefzuhri.suitmediainterntest.data.model.User
import com.ariefzuhri.suitmediainterntest.databinding.ItemUserBinding

class UserAdapter(private val eventListener: EventListener) :
    PagingDataAdapter<User, UserAdapter.UserViewHolder>(UserComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }

    object UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                initView(user)
                initClickListeners(user)
            }
        }

        private fun ItemUserBinding.initView(user: User) {
            tvName.text = user.name
            tvEmail.text = user.email
            imgAvatar.loadAvatar(user.avatar)
        }

        private fun ItemUserBinding.initClickListeners(user: User) {
            root.setOnClickListener {
                eventListener.onItemClicked(user)
            }
        }
    }

    interface EventListener {

        fun onItemClicked(user: User)
    }
}
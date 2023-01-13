package com.rchyn.testsuitmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rchyn.testsuitmedia.databinding.ItemRowUserBinding
import com.rchyn.testsuitmedia.model.User

class ListUserAdapter(
    private val onClickItem: (User) -> Unit
) : PagingDataAdapter<User, ListUserAdapter.ListUserViewHolder>(DiffCallback) {

    inner class ListUserViewHolder(
        private val binding: ItemRowUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                Glide.with(binding.root)
                    .load(user.avatar)
                    .into(ivAvatar)
                tvUsername.text = user.name
                tvEmail.text = user.email.uppercase()

                root.setOnClickListener { onClickItem(user) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(it)
        }
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

    }
}
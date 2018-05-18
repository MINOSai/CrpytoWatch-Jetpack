package com.minosai.archusers.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.minosai.archusers.R
import com.minosai.archusers.model.User
import com.minosai.archusers.utils.inflate

/**
 * Created by minos.ai on 16/05/18.
 */

class UserAdapter(context: Context) : PagedListAdapter<User, UserAdapter.UserViewHolder>(
        object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User?, newItem: User?) = oldItem?.id == newItem?.id
            override fun areContentsTheSame(oldItem: User?, newItem: User?) = oldItem == newItem
        }
) {
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }

    //TODO: Use different layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent.inflate(android.R.layout.simple_list_item_1))

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) = with(itemView) {
            //TODO: Bind Views
        }
    }
}
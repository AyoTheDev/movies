package com.ayo.movies.ui.movies.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ayo.domain.model.UserDomain
import kotlinx.android.synthetic.main.user_item.view.*


class UserViewHolder(itemView: View, private val listener: UserListAdapter.Listener):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    fun bind(user: UserDomain){
        itemView.apply {
            setOnClickListener(this@UserViewHolder)
            reputationTV.text = user.reputation.toString()
            usernameTV.text = user.displayName.trim()
        }
    }

    override fun onClick(v: View?) {
        listener.onClick(layoutPosition)
    }
}
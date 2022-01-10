package com.example.feature_search.history_users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.github.GitUserModel
import com.example.uikit.databinding.CustomCardUserListBinding
import com.example.uikit.extensions.layoutInflater
import com.example.uikit.extensions.loadUrl

class GithubListUsersAdapter(
    private var list: List<GitUserModel>
//    private var onItemClick: (GitUserModel) -> Unit
) : RecyclerView.Adapter<GithubListUsersAdapter.GithubListUsersViewHolder>() {


    var positionSelected = -1
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    @SuppressLint("NotifyDataSetChanged")
    fun updateUserList(list: List<GitUserModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = GithubListUsersViewHolder(CustomCardUserListBinding.inflate(parent.context.layoutInflater))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: GithubListUsersViewHolder, position: Int) {
        holder.bind(data = list[position])
    }

    inner class GithubListUsersViewHolder(val binding: CustomCardUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GitUserModel ){
            binding.itemsRv.name = data.gitUserData.user.name
            binding.itemsRv.bio = data.gitUserData.user.bio
            binding.itemsRv.login = data.gitUserData.user.login
            binding.itemsRv.profilePic?.loadUrl(data.gitUserData.user.avatar_url)

//            binding.itemsRv.card.setOnClickListener {
//                onItemClick.invoke(data)
//            }
        }
    }
}



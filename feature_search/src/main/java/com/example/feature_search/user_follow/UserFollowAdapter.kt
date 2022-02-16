package com.example.feature_search.user_follow

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel
import com.example.uikit.databinding.CardGithubUserBinding
import com.example.uikit.extensions.layoutInflater
import com.example.uikit.extensions.loadUrlWithCircular

class UserFollowAdapter(
    private var list: List<SearchUserModel>,
    private var onItemClick: (String) -> Unit
    ) : RecyclerView.Adapter<UserFollowAdapter.GithubListUsersViewHolder>() {

        @SuppressLint("NotifyDataSetChanged")
        fun updateUserList(list: List<SearchUserModel>) {
            this.list = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): GithubListUsersViewHolder {
            return GithubListUsersViewHolder(
                CardGithubUserBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: GithubListUsersViewHolder, position: Int) {
            holder.bind(data = list[position])
        }

        inner class GithubListUsersViewHolder(
            private val binding: CardGithubUserBinding
        ) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(data: SearchUserModel) {
                binding.image.loadUrlWithCircular(data.avatar_url)
                binding.tvName.text = data.name ?: ""
                binding.tvBio.text = data.bio ?: ""
                binding.tvLogin.text = data.login

                binding.layout.setOnClickListener {
                    onItemClick.invoke(data.login!!)
                }
            }
        }

}
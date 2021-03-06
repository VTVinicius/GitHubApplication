package com.example.feature_search.history_users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.github.GitUserModel
import com.example.uikit.databinding.CardGithubUserBinding
import com.example.uikit.extensions.layoutInflater
import com.example.uikit.extensions.loadUrlWithCircular

class GithubListUsersAdapter(
    private var list: List<GitUserModel>,
    private var onItemClick: (Long) -> Unit
) : RecyclerView.Adapter<GithubListUsersAdapter.GithubListUsersViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateUserList(list: List<GitUserModel>) {
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

        fun bind(data: GitUserModel) {
            binding.image.loadUrlWithCircular(data.gitUserData.user.avatar_url)
            binding.tvName.text = data.gitUserData.user.name ?: ""
            binding.tvBio.text = data.gitUserData.user.bio ?: ""
            binding.tvLogin.text = data.gitUserData.user.login

            binding.layout.setOnClickListener {
                onItemClick.invoke(data.gitUserData.user.id)
            }
        }
    }
}



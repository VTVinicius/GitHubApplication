package com.example.feature_search.user_repos

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.github.UserReposModel
import com.example.uikit.databinding.CardUserReposBinding
import com.example.uikit.extensions.layoutInflater

class UserReposAdapter(
    private var list: List<UserReposModel>,
) : RecyclerView.Adapter<UserReposAdapter.UserReposViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateReposList(list: List<UserReposModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserReposViewHolder {
        return UserReposViewHolder(
            CardUserReposBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserReposViewHolder, position: Int) {
        holder.bind(data = list[position])
    }

    inner class UserReposViewHolder(
        private val binding: CardUserReposBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: UserReposModel) {
            binding.tvName.text = data.repoName ?: ""
            binding.tvDescription.text = data.description ?: ""
            binding.tvLanguage.text = data.language ?: ""
        }
    }
}


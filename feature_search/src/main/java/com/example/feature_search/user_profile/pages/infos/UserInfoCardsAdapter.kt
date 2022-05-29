package com.example.feature_search.user_profile.pages.infos

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.github.GitUserModel
import com.example.uikit.databinding.CustomCardUserInfosBinding
import com.example.uikit.extensions.layoutInflater


class UserInfoCardsAdapter(
    private var list: List<GitUserModel>,
    private var onItemClick: (Long) -> Unit
) : RecyclerView.Adapter<UserInfoCardsAdapter.ProfileCardsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateUserList(list: List<GitUserModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileCardsViewHolder {
        return ProfileCardsViewHolder(
            CustomCardUserInfosBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ProfileCardsViewHolder, position: Int) {
        holder.bind(data = list[position])
    }

    inner class ProfileCardsViewHolder(
        private val binding: CustomCardUserInfosBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GitUserModel) {


        }
    }
}
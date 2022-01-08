package com.example.uikit.list_github_user

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uikit.databinding.CustomCardUserListBinding
import com.example.uikit.extensions.layoutInflater

class GithubListUsersAdapter(
    private var listener: GithubSelectList
) : RecyclerView.Adapter<GithubListUsersAdapter.GithubListUsersViewHolder>() {
    val githubUsersArray = ArrayList<GithubListUsers>()

    var positionSelected = -1
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var userList = emptyList<GithubListModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = GithubListUsersViewHolder(CustomCardUserListBinding.inflate(parent.context.layoutInflater))

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: GithubListUsersViewHolder, position: Int) {
        userList[position].let { holder.bind(it) }
    }

    inner class GithubListUsersViewHolder(val binding: CustomCardUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: GithubListModel) {
            binding.itemsRv.name = user.nameRes.toString()
            binding.itemsRv.bio = user.bioRes.toString()
            binding.itemsRv.login = user.loginRes.toString()

            binding.itemsRv.card.setOnClickListener {
                listener.onChangedSelectListener(userList[adapterPosition])
                positionSelected = adapterPosition
            }
        }
    }
}



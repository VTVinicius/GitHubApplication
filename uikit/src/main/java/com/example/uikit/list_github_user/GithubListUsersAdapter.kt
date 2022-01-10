package com.example.uikit.list_github_user

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.uikit.R
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
        userList[position].let { holder.bind("", "", "") }
    }

    inner class GithubListUsersViewHolder(val binding: CustomCardUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(login: String?, name: String?, bio: String?){
            binding.itemsRv.name = name ?: ""
            binding.itemsRv.bio = bio ?: ""
            binding.itemsRv.login = login ?: ""

            binding.itemsRv.card.setOnClickListener {
                listener.onChangedSelectListener(userList[adapterPosition])
                positionSelected = adapterPosition
            }
        }
    }
}



package com.example.feature_search.user_profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class UserProfileHostAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
                UserProfileFragment()
            }
            1 -> {
                UserProfileCommunityFragment()
            }
            else -> {
                UserProfileRepositoriesFragment()
            }
        }
    }
}
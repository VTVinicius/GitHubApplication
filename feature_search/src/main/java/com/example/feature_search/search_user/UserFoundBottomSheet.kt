package com.example.feature_search.search_user

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import com.example.base_feature.core.BaseBottomSheet
import com.example.base_feature.utils.delegateproperties.navDirections
import com.example.feature_search.commom.navigation.FeatureSearchNavigation
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.feature_search.search_user.UserFoundBottomSheet.Companion.Args.Companion.fromBundle
import com.example.feature_search.databinding.BottomSheetUserInfoBinding
import com.example.uikit.extensions.loadUrlWithCircular
import kotlinx.parcelize.Parcelize

class UserFoundBottomSheet :  BaseBottomSheet<BottomSheetUserInfoBinding>(){

    private val args by lazy { fromBundle(arguments) }
    private val navigation : MobileNavigation by navDirections()


    override fun onCreateViewBinding(inflater: LayoutInflater): BottomSheetUserInfoBinding =
        BottomSheetUserInfoBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()

        val userId: Long = args.userInfo!!.id

        onClick(userId)

        binding.imgProfilePicture.loadUrlWithCircular(args.userInfo?.avatar_url)
        binding.tvUserLogin.text = args.userInfo?.login
        binding.tvUserBio.text = args.userInfo?.bio ?: "..."
        binding.tvUserName.text = args.userInfo?.name ?: "..."

    }


    private fun onClick(userID: Long){
        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.btnSeeMore.setOnClickListener {
        navigation.goToUserProfile(userID)
        }

    }
    companion object {

        @Parcelize
        data class Args(
            var userInfo: UserInfoModel? = null,

            ) : Parcelable {
            fun toBundle() = Bundle().also { it.putParcelable(ARGS, this) }

            companion object {
                private const val ARGS = "args"

                fun fromBundle(args: Bundle?) =
                    args?.getParcelable(ARGS) ?: Args()
            }
        }

        fun newInstance(
            userInfo: UserInfoModel,
        ): UserFoundBottomSheet {
            val args = Args(userInfo)
            return UserFoundBottomSheet().apply {
                arguments = args.toBundle()
            }
        }
    }
}

package com.example.feature_search.commom.user_info

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import com.example.base_feature.core.BaseBottomSheet
import com.example.feature_search.commom.user_info.UserFoundBottomSheet.Companion.Args.Companion.fromBundle
import com.example.feature_search.databinding.BottomSheetUserInfoBinding
import com.example.uikit.extensions.loadUrlWithCircular
import kotlinx.parcelize.Parcelize

class UserFoundBottomSheet :  BaseBottomSheet<BottomSheetUserInfoBinding>(){

    private val args by lazy { fromBundle(arguments) }

    override fun onCreateViewBinding(inflater: LayoutInflater): BottomSheetUserInfoBinding =
        BottomSheetUserInfoBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()

        binding.imgProfilePicture.loadUrlWithCircular("${args.userInfo?.avatar_url}")
        binding.tvUserLogin.text = "${args.userInfo?.login}"
        binding.tvBio.text = "${args.userInfo?.bio}"


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
                    args?.getParcelable(ARGS) ?: UserFoundBottomSheet.Companion.Args()
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

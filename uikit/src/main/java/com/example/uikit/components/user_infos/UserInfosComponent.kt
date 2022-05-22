package com.example.uikit.components.user_infos

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.uikit.R
import com.example.uikit.databinding.CardGithubUserBinding
import com.example.uikit.databinding.CustomCardUserInfosBinding
import com.example.uikit.extensions.layoutInflater


open class UserInfosComponent @JvmOverloads constructor(
        context: Context,
        attributes: AttributeSet? = null,
        defStyleAttr: Int = 0,
    ) : ConstraintLayout(context, attributes, defStyleAttr) {


        private val binding by lazy {
            CustomCardUserInfosBinding.inflate(context.layoutInflater, this, false)
        }





        init {
            setUpViews(attributes)
        }

        private fun setUpViews(attrs: AttributeSet?) {
            attrs?.apply {
                with(context.obtainStyledAttributes(this, R.styleable.GithubListUsers)) {
                recycle()
                }
            }
        }
    }

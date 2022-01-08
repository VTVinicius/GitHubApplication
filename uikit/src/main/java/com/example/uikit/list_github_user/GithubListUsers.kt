package com.example.uikit.list_github_user

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.uikit.R
import com.example.uikit.databinding.CardGithubUserBinding
import com.example.uikit.extensions.layoutInflater

open class GithubListUsers @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributes, defStyleAttr) {


    private val binding by lazy {
        CardGithubUserBinding.inflate(context.layoutInflater, this)
    }

    var card = binding.layout

    var name: String = ""
        set(value) {
            field = value
            binding.tvName.text = value
        }

    var login: String = ""
        set(value) {
            field = value
            binding.tvLogin.text = value
        }

    var bio: String = ""
        set(value) {
            field = value
            binding.tvBio.text = value
        }

    init {
        setUpViews(attributes)
    }

    private fun setUpViews(attrs: AttributeSet?) {
        attrs?.apply {
            with(context.obtainStyledAttributes(this, R.styleable.GithubListUsers)) {
                name = getString(R.styleable.GithubListUsers_name) ?: "Nome Pessoal"
                login = getString(R.styleable.GithubListUsers_login) ?: "Nome Github"
                bio = getString(R.styleable.GithubListUsers_bio) ?: "Sem Biografia"
                recycle()
            }
        }
    }
}
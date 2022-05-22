package com.example.uikit.components.list_repos

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.uikit.R
import com.example.uikit.databinding.CardUserReposBinding
import com.example.uikit.extensions.layoutInflater

open class ListRepos @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributes, defStyleAttr) {

    private val binding by lazy {
        CardUserReposBinding.inflate(context.layoutInflater, this, false)
    }


    var name: String? = ""
        set(value) {
            field = value
            binding.tvName.text = value
        }

    var description: String? = ""
        set(value) {
            field = value
            binding.tvDescription.text = value
        }

    var language: String? = ""
        set(value) {
            field = value
            binding.tvLanguage.text = value
        }

    init {
        setUpViews(attributes)
    }

    private fun setUpViews(attrs: AttributeSet?) {
        attrs?.apply {
            with(context.obtainStyledAttributes(this, R.styleable.ListRepos)) {
                name = getString(R.styleable.ListRepos_nameUser) ?: ""
                description = getString(R.styleable.ListRepos_description) ?: ""
                language = getString(R.styleable.ListRepos_language) ?: ""
                recycle()
            }
        }
    }

}
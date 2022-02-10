package com.example.base_feature.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition


fun TextView.addDrawableStart(drawable: Drawable?) {
    setCompoundDrawables(drawable, null, null, null)
}

fun TextView.addDrawableStart(resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(
        ContextCompat.getDrawable(context, resId), null, null, null
    )
}

fun TextView.loadDrawableStart(
    url: String?,
    @DrawableRes resPlaceholder: Int? = null
) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .apply {
            resPlaceholder?.let { placeholder(it) }
        }
        .fitCenter()
        .into(object : Target<Drawable> {
            override fun onStart() = Unit

            override fun onStop() = Unit

            override fun onDestroy() = Unit

            override fun onLoadStarted(placeholder: Drawable?) {
                addDrawableStart(placeholder)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                addDrawableStart(errorDrawable)
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                addDrawableStart(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) = Unit

            override fun getSize(cb: SizeReadyCallback) = Unit

            override fun removeCallback(cb: SizeReadyCallback) = Unit

            override fun setRequest(request: Request?) = Unit

            override fun getRequest(): Request? = null
        })
}

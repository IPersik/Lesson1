package com.example.lesson1.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lesson1.R
import com.example.lesson1.interfaces.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(1500))
            .circleCrop()
            .into(container)
    }

    override fun loadInfo(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(1500))
            .circleCrop()
            .into(container)
    }

}
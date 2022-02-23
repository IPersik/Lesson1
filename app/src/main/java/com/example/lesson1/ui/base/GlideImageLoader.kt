package com.example.lesson1.ui.base

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.lesson1.R
import com.example.lesson1.interfaces.IImageLoader


class GlideImageLoader : IImageLoader<ImageView> {

    override fun loadInfo(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(1500))
            .circleCrop()
            .into(container)
    }
}
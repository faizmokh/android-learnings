package com.faizmokhtar.catnetwork

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(imageUrl: String, imageView: ImageView)
}

package com.faizmokhtar.weatherapp

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(imageUrl: String, imageView: ImageView)
}

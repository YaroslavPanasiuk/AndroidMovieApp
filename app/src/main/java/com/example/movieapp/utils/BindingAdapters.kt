package com.example.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?){
    url?.let {
        Glide.with(view).load(Constants.POSTER_BASE_URL + url).into(view)
    }
}
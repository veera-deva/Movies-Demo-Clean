package com.demo.movies.di

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.demo.movies.R

/**
 * Binding adapter function to load image url from layout
 * */

@BindingAdapter("bind:loadImage")
fun loadImageUrl(imageView: AppCompatImageView, imageUrl: String?) {
    Glide.with(imageView.context).load(imageUrl)
        .placeholder(R.drawable.ic_placeholder).into(imageView)
}


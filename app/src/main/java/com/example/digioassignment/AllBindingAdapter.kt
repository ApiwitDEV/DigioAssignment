package com.example.digioassignment

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("setImage")
fun setImage(view : ImageView, image : String?) {
    Picasso.get().load(image).into(view)
}
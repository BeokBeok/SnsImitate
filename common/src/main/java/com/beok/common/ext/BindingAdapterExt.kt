package com.beok.common.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beok.common.base.BaseAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind_replaceItem")
fun replaceItem(recyclerView: RecyclerView, item: List<Any>?) {
    if (item == null) return

    @Suppress("UNCHECKED_CAST")
    (recyclerView.adapter as BaseAdapter<Any>).run {
        replaceItems(item)
        notifyDataSetChanged()
    }
}

@BindingAdapter("bind_imageUrlAsGlide")
fun showImageForGlide(imageView: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) return

    Glide.with(imageView)
        .load(imageUrl)
        .error(android.R.drawable.ic_menu_gallery)
        .into(imageView)
}

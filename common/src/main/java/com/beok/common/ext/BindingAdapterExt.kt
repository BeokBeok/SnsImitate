package com.beok.common.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beok.common.base.BaseAdapter

@BindingAdapter("bind_replaceItem")
fun replaceItem(recyclerView: RecyclerView, item: List<Any>?) {
    if (item == null) return

    @Suppress("UNCHECKED_CAST")
    (recyclerView.adapter as BaseAdapter<Any>).run {
        replaceItems(item)
        notifyDataSetChanged()
    }
}

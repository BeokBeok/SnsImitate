package com.beok.common.base

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<ITEM : Any>(
    private val layoutBindingId: Pair<Int, Int>,
    private val viewModels: Map<Int, ViewModel>
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val items = mutableListOf<ITEM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(
            parent,
            layoutBindingId,
            viewModels
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindViewHolder(items[position])
    }

    fun replaceItems(items: List<ITEM>?) {
        if (items == null) return

        this.items.run {
            clear()
            addAll(items)
        }
    }
}

class BasePagingAdapter<ITEM : Any>(
    private val layoutBindingId: Pair<Int, Int>,
    private val viewModels: Map<Int, ViewModel>,
    diffUtil: DiffUtil.ItemCallback<ITEM>
) : PagedListAdapter<ITEM, BaseViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(
            parent,
            layoutBindingId,
            viewModels
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindViewHolder(getItem(position))
    }
}

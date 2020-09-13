package com.beok.common.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(
    parent: ViewGroup,
    private val layoutBindingId: Pair<Int, Int>,
    private val viewModels: Map<Int, ViewModel>
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(layoutBindingId.first, parent, false)
) {
    private val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

    fun bindViewHolder(item: Any?) {
        if (item == null) return
        binding.setVariable(layoutBindingId.second, item)

        for (key in viewModels.keys) {
            binding.setVariable(key, viewModels[key])
        }
    }
}

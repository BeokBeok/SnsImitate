package com.beok.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    protected lateinit var binding: VDB
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<VDB>(inflater, layoutId, container, false)
        .apply { lifecycleOwner = viewLifecycleOwner }
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    open fun setupObserver() {
        viewModel.toastMsg.observe(viewLifecycleOwner, {
            if (it.isResource) {
                Toast.makeText(
                    this@BaseFragment.context,
                    getString(it.message.toInt()),
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            Toast.makeText(this@BaseFragment.context, it.message, Toast.LENGTH_SHORT).show()
        })
    }
}

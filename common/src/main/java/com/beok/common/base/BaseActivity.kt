package com.beok.common.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {

    protected val binding: VDB by lazy {
        DataBindingUtil.setContentView<VDB>(this, layoutId).apply {
            lifecycleOwner = this@BaseActivity
        }
    }
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    open fun setupObserver() {
        viewModel.toastMsg.observe(this, {
            if (it.isResource) {
                Toast.makeText(this, getString(it.message.toInt()), Toast.LENGTH_SHORT).show()
                return@observe
            }
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
    }
}

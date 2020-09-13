package com.beok.snsimitate.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.beok.common.base.BaseActivity
import com.beok.snsimitate.R
import com.beok.snsimitate.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>(R.layout.activity_auth) {

    override val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun setupObserver() {
        super.setupObserver()
        viewModel.isSuccessLogin.observe(this, {
            if (it) {
                setResult(RESULT_OK)
                finish()
            }
        })
    }

    private fun setupViewModel() {
        binding.vm = viewModel.apply {
            setupLoginMode(intent.getBooleanExtra(AUTH_IS_LOGIN, false))
        }
    }

    companion object {
        private const val AUTH_IS_LOGIN = "AUTH_IS_LOGIN"

        fun newIntent(context: Context?, isLogin: Boolean) =
            Intent(context, AuthActivity::class.java).apply {
                putExtra(AUTH_IS_LOGIN, isLogin)
            }
    }
}

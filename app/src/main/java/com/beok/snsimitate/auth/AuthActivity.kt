package com.beok.snsimitate.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.beok.snsimitate.R
import com.beok.snsimitate.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        binding.lifecycleOwner = this
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

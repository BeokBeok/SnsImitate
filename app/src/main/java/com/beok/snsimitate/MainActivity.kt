package com.beok.snsimitate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.beok.snsimitate.auth.AuthActivity
import com.beok.snsimitate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setupClickListener()
    }

    private fun setupClickListener() {
        binding.btnMainLogin.setOnClickListener {
            startActivity(AuthActivity.newIntent(this, isLogin = true))
        }
        binding.btnMainSignUp.setOnClickListener {
            startActivity(AuthActivity.newIntent(this, isLogin = false))
        }
    }
}

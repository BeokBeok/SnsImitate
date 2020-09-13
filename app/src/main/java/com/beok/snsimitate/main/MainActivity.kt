package com.beok.snsimitate.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.beok.snsimitate.R
import com.beok.snsimitate.auth.AuthActivity
import com.beok.snsimitate.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupClickListener()
        setupViewPager()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) return
        if (requestCode == REQ_AUTH) {
            Toast.makeText(this, getString(R.string.msg_login_success), Toast.LENGTH_SHORT).show()
            toggleLogoutComponent(true)
        }
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
            }
    }

    private fun setupViewPager() {
        val tabTitles = listOf(getString(R.string.title_home), getString(R.string.title_cards))
        binding.vpContent.adapter = MainViewPagerAdapter(this)
        TabLayoutMediator(binding.tlContent, binding.vpContent) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun setupClickListener() {
        binding.btnMainLogin.setOnClickListener {
            startActivityForResult(AuthActivity.newIntent(this, isLogin = true), REQ_AUTH)
        }
        binding.btnMainSignUp.setOnClickListener {
            startActivityForResult(AuthActivity.newIntent(this, isLogin = false), REQ_AUTH)
        }
        binding.btnMainLogout.setOnClickListener {
            toggleLogoutComponent(false)
            Toast.makeText(this, getString(R.string.msg_logout), Toast.LENGTH_SHORT).show()
        }
    }

    private fun toggleLogoutComponent(isOn: Boolean) {
        binding.btnMainSignUp.isVisible = !isOn
        binding.btnMainLogin.isVisible = !isOn
        binding.btnMainLogout.isVisible = isOn
    }

    companion object {
        const val REQ_AUTH = 362
    }
}

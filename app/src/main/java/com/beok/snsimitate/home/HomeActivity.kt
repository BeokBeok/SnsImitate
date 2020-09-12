package com.beok.snsimitate.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.beok.common.base.BaseActivity
import com.beok.snsimitate.R
import com.beok.snsimitate.auth.AuthActivity
import com.beok.snsimitate.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupClickListener()
        setupViewPager()
    }

    private fun setupViewPager() {
        val tabTitles = listOf(getString(R.string.title_home), getString(R.string.title_cards))
        binding.vpContent.adapter = HomeViewPagerAdapter(supportFragmentManager, tabTitles)
        binding.tlContent.setupWithViewPager(binding.vpContent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) return
        if (requestCode == REQ_AUTH) {
            Toast.makeText(this, getString(R.string.msg_login_success), Toast.LENGTH_SHORT).show()
            toggleLogoutComponent(true)
        }
    }

    private fun setupClickListener() {
        binding.btnHomeLogin.setOnClickListener {
            startActivityForResult(AuthActivity.newIntent(this, isLogin = true), REQ_AUTH)
        }
        binding.btnHomeSignUp.setOnClickListener {
            startActivityForResult(AuthActivity.newIntent(this, isLogin = false), REQ_AUTH)
        }
        binding.btnHomeLogout.setOnClickListener {
            toggleLogoutComponent(false)
        }
    }

    private fun toggleLogoutComponent(isOn: Boolean) {
        binding.btnHomeSignUp.isVisible = !isOn
        binding.btnHomeLogin.isVisible = !isOn
        binding.btnHomeLogout.isVisible = isOn
    }

    companion object {
        const val REQ_AUTH = 362
    }
}

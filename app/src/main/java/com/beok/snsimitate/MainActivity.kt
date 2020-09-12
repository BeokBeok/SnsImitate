package com.beok.snsimitate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.beok.common.base.BaseActivity
import com.beok.snsimitate.auth.AuthActivity
import com.beok.snsimitate.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupClickListener()
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
        binding.btnMainLogin.setOnClickListener {
            startActivityForResult(AuthActivity.newIntent(this, isLogin = true), REQ_AUTH)
        }
        binding.btnMainSignUp.setOnClickListener {
            startActivityForResult(AuthActivity.newIntent(this, isLogin = false), REQ_AUTH)
        }
        binding.btnMainLogout.setOnClickListener {
            toggleLogoutComponent(false)
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

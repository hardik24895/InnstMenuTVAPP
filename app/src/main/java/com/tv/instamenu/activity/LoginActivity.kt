package com.tv.instamenu.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.eisuchi.extention.validateEmail
import com.hardik.newsapp.extension.getValue
import com.hardik.newsapp.extension.goToActivity
import com.hardik.newsapp.extension.goToActivityAndClearTask
import com.hardik.newsapp.extension.isEmpty
import com.hardik.newsapp.ui.base.BaseActivity
import com.hardik.newsapp.utils.Util.showInfoAlert
import com.tv.instamenu.data.modal.LoginModal
import com.tv.instamenu.databinding.ActivityMainBinding
import com.tv.instamenu.viewmodal.LoginViewModal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    val loginViewModal: LoginViewModal by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        if (session.isLoggedIn) goToActivityAndClearTask<ScreenSelectionActivity>()

        binding.btnLogin.setOnClickListener {
            validation()
        }

        subscribeToEvents()
    }

    fun validation() {
        if (binding.edtEmail.isEmpty()) {
            binding.edtEmail.error = "Enter Email"

        } else if (!validateEmail(binding.edtEmail.text.toString())) {
            binding.edtEmail.error = "Enter Valid Email"

        } else if (binding.edtPassword.isEmpty()) {
            binding.edtPassword.error = "Enter Password"

        } else {
            showProgressbar()
            loginViewModal.login(binding.edtEmail.getValue(), binding.edtPassword.getValue())
        }
    }

    /**
     * Get Flow Event
     */
    private fun subscribeToEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModal.loginEvent.collect { event ->
                    when (event) {
                        is LoginViewModal.LoginEvent.OnSuccess -> onSuccess(event.response)
                        is LoginViewModal.LoginEvent.OnError -> onFailed(event.error)
                    }

                }
            }
        }
    }

    private fun onSuccess(response: LoginModal) {
        hideProgressbar()
        if (response.status == 1) {
            session.isLoggedIn = true
            session.user = response
            goToActivity<ScreenSelectionActivity>()
        } else {
            showInfoAlert(this, response.data?.msg.toString(), false)
        }

    }

    private fun onFailed(response: String) {
        showInfoAlert(this, response, false)
    }

}
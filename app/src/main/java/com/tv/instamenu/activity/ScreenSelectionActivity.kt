package com.tv.instamenu.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.hardik.newsapp.extension.goToActivity
import com.hardik.newsapp.extension.goToActivityAndClearTask
import com.hardik.newsapp.ui.base.BaseActivity
import com.hardik.newsapp.utils.Util
import com.tv.instamenu.adapter.ScreenAdapter
import com.tv.instamenu.data.modal.LoginModal
import com.tv.instamenu.data.modal.ScreenItem
import com.tv.instamenu.data.modal.ScreenModal
import com.tv.instamenu.databinding.ActivityScreenSelectionBinding
import com.tv.instamenu.viewmodal.LoginViewModal
import com.tv.instamenu.viewmodal.ScreensViewModal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScreenSelectionActivity : BaseActivity(), ScreenAdapter.OnItemSelected {
    private lateinit var screenAdapter: ScreenAdapter
    private val medeaList = mutableListOf<ScreenItem>()
    private lateinit var binding: ActivityScreenSelectionBinding
    val screensViewModal: ScreensViewModal by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenSelectionBinding.inflate(layoutInflater)
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
        setupIncomingOrderRecyclerView()

        showProgressbar()
        screensViewModal.screen(session.user.data?.id.toString())

        subscribeToEvents()

        binding.btnLogout.setOnClickListener {
            showProgressbar()
            screensViewModal.logOut(session.user.data?.id.toString())
        }
    }

    /**
     * Get Flow Event
     */
    private fun subscribeToEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                screensViewModal.screenEvent.collect { event ->
                    when (event) {
                        is ScreensViewModal.ScreenEvent.OnSuccess -> onSuccess(event.response)
                        is ScreensViewModal.ScreenEvent.OnError -> onFailed(event.error)
                    }

                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                screensViewModal.loginEvent.collect { event ->
                    when (event) {
                        is ScreensViewModal.LoginEvent.OnSuccess -> onSuccessLogout(event.response)
                        is ScreensViewModal.LoginEvent.OnError -> onFailed(event.error)
                    }

                }
            }
        }

    }

    private fun onSuccessLogout(response: LoginModal) {
        hideProgressbar()
        if (response.status == 1) {
            session.clearSession()
            goToActivityAndClearTask<LoginActivity>()
        } else {
            Util.showInfoAlert(this, response.data.toString(), false)
        }

    }
  private fun onSuccess(response: ScreenModal) {
        hideProgressbar()
        if (response.status == 1) {
            screenAdapter.addItems(response.data)
        } else {
            Util.showInfoAlert(this, response.data.toString(), false)
        }

    }

    private fun onFailed(response: String) {
        Util.showInfoAlert(this, response, false)
    }


    private fun setupIncomingOrderRecyclerView() {
        screenAdapter = ScreenAdapter(this, medeaList, this)
        binding.rv.apply {

            layoutManager =
                GridLayoutManager(
                    this@ScreenSelectionActivity,
                    2,
                    GridLayoutManager.VERTICAL,
                    false
                )
            adapter = screenAdapter
            //  setHasFixedSize(true)
        }
    }

    override fun completeOrderClick(
        position: Int,
        data: ScreenItem,
        applyTime: Int,
        action: String
    ) {


       goToAutoSliderActivity(data.id.toString())
    }

    private fun goToAutoSliderActivity(id: String) {
        startActivity(AutoSliderActivity.getStartIntent(this, id))
    }
}
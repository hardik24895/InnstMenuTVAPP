package com.hardik.newsapp.ui.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.tv.instamenu.dialog.ProgressDialog
import com.hardik.newsapp.extension.dismissAlertDialog
import com.tv.instamenu.utils.SessionManager


/**
 * Created by Hardik
 */

open class BaseActivity : AppCompatActivity() {
     var shouldPerformDispatchTouch = true
    var progressDialog: ProgressDialog? = null
    lateinit var session: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disableAutoFill()
        session = SessionManager(this)

    }

    override fun onPause() {
        super.onPause()
        hideSoftKeyboard()
    }

    override fun onDestroy() {
        dismissAlertDialog()
        hideSoftKeyboard()
        super.onDestroy()
    }

    fun showProgressbar() {
        hideProgressbar()
        progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    fun hideProgressbar() {
        if (progressDialog != null && progressDialog?.isShowing!!) progressDialog!!.dismiss()
    }

    fun showSoftKeyboard(view: EditText) {
        view.requestFocus(view.text.length)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideSoftKeyboard(): Boolean {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            return imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            return false
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        var ret = false
        try {
            val view = currentFocus
            ret = super.dispatchTouchEvent(event)
            if (shouldPerformDispatchTouch) {
                if (view is EditText) {
                    val w = currentFocus
                    val scrCords = IntArray(2)
                    if (w != null) {
                        w.getLocationOnScreen(scrCords)
                        val x = event.rawX + w.left - scrCords[0]
                        val y = event.rawY + w.top - scrCords[1]

                        if (event.action == MotionEvent.ACTION_UP && (x < w.left || x >= w.right || y < w.top || y > w.bottom)) {
                            val imm =
                                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                        }
                    }
                }
            }
            return ret
        } catch (e: Exception) {
            e.printStackTrace()
            return ret
        }

    }

    fun disableAutoFill() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.decorView.importantForAutofill =
                View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        }
    }

    companion object {

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        }
    }




}

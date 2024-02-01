package com.tv.instamenu.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar
import com.tv.instamenu.R
import com.tv.instamenu.databinding.CommonDialogProgressBinding


/**
 * Created by Hardik
 */

class ProgressDialog @JvmOverloads constructor(
    context: Context,
) :
    AlertDialog(context, R.style.ProgressDialog) {

    lateinit var binding: CommonDialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CommonDialogProgressBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
       // setContentView(R.layout.common_dialog_progress)
        setCanceledOnTouchOutside(false)
        setCancelable(false)


    }

    fun getProgressBar(): ProgressBar {
        return binding.progressBar
    }


}

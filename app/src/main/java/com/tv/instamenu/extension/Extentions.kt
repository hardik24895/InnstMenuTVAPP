package com.hardik.newsapp.extension

import android.app.AlertDialog
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.tv.instamenu.R


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun EditText.getValue(): String {
    return this.text.toString().trim()
}

fun TextView.getValue(): String {
    return this.text.toString().trim()
}

fun EditText.isEmpty(): Boolean {
    return this.text.trim().isEmpty()
}

fun TextView.isEmpty(): Boolean {
    return this.text.isNullOrEmpty()
}

fun View.showSnackBar(context: Context,msg: String, duration: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(this, msg, duration)
    val sbView = snack.view
    snack.setBackgroundTint(ContextCompat.getColor(context, R.color.black))
    val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(Color.WHITE)
    snack.show()
}



fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

var dialog: AlertDialog? = null
fun AppCompatActivity.showAlert(msg: String, cancelable: Boolean = false) {
    dialog = AlertDialog.Builder(this)
        .setMessage(msg)
        .setCancelable(cancelable)
        .setPositiveButton(
            getString(R.string.ok)
        ) { dialog, which -> dialog.dismiss() }
        .create()
    dialog?.show()
}


fun Fragment.showAlert(msg: String, cancelable: Boolean = false) {
    dismissAlertDialog()
    dialog = AlertDialog.Builder(context)
        .setTitle(context?.getString(R.string.app_name))
        .setMessage(msg)
        .setCancelable(cancelable)
        .setPositiveButton(
            getString(R.string.ok)
        ) { dialog, which -> dialog.dismiss() }
        .create()
    dialog?.show()
}

fun dismissAlertDialog() {
    dialog?.dismiss()
}

 fun AssetManager.readAssetsFile(fileName: String): String =
    open(fileName).bufferedReader().use { it.readText() }








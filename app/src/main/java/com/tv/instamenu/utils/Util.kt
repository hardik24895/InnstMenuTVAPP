package com.hardik.newsapp.utils

import android.app.Activity
import android.app.AlertDialog
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.hardik.newsapp.utils.AppConstant.LAST_CLICK_ITEM
import com.hardik.newsapp.utils.AppConstant.MULTIPLE_CLICK_THRESHOLD
import com.tv.instamenu.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object Util {


    fun jsonData(json: String): String {
        var error = "null"
        try {
            val obj = JSONObject(json)
            val keys = obj.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val value = obj[key]
                error = if (value is JSONArray)
                    obj.getJSONArray(key).join(",").replace("\"".toRegex(), "")
                else

                    obj.getString(key)

            }
        } catch (e: JSONException) {
            return "Request failed with error code: 400 Bad Request"
        }

        return error


    }

    /**
     * Method to set same OnClickListener on multiple views
     *
     * @param listener
     * @param views
     */
    fun setOnClickListener(listener: View.OnClickListener?, vararg views: View) {
        for (view in views) {
            view.setOnClickListener(listener)
        }
    }

    /**
     * Method to prevent multiple clicks
     */
    fun preventMultipleClicks(): Boolean {
        // Preventing multiple clicks, using threshold of MULTIPLE_CLICK_THRESHOLD  second
        if (SystemClock.elapsedRealtime() - LAST_CLICK_ITEM < MULTIPLE_CLICK_THRESHOLD) {
            return false
        }
        LAST_CLICK_ITEM = SystemClock.elapsedRealtime()
        return true
    }
    fun showInfoAlert(activity: Activity, msg: String, isSuccess: Boolean) {
        val builder = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
            .create()

        val view = activity.layoutInflater.inflate(R.layout.dialog_info, null)
        val button = view.findViewById<LinearLayout>(R.id.ok)
        val img = view.findViewById<ImageView>(R.id.img)
        if (isSuccess) img.setImageResource(R.drawable.ic_tick)
        else img.setImageResource(R.drawable.ic_red_cross)
        val txt = view.findViewById<TextView>(R.id.tvMessage)
        txt.text = msg
        builder.setView(view)
        button.setOnClickListener {
            builder.dismiss()
        }
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }

}
package com.tv.instamenu.data.api


import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hardik.newsapp.extension.showToast
import com.tv.instamenu.utils.Logger
import com.tv.instamenu.activity.LoginActivity
import com.tv.instamenu.utils.SessionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject


class SessionInterceptor @Inject constructor(@ApplicationContext context: Context) : Interceptor {

    private val context = context.applicationContext

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val session = SessionManager(context)
        val builder = original.newBuilder()
        builder.header("Accept", "application/json")
       /* if (session.isLoggedIn) {
            builder.header("Authorization", token.toString())
        }*/
        val request = builder.build()
        val response = chain.proceed(request)
        if (response.code == 401) {
            Logger.e("Session Expired : Endpoint", response.request.url.encodedPath)

            val intent = Intent(context, LoginActivity::class.java)
            try {
                val jsonObject = response.body?.string()?.let { JSONObject(it) }
                val msg = jsonObject?.optString("msg")
                //  intent.putExtra(Constant.ERROR, msg)
            } catch (e: Exception) {
                // intent.putExtra(Constant.ERROR, "${e.message}")
                context.showToast(e.message.toString(), Toast.LENGTH_LONG)
            }
            context.showToast("UnAuthorized", Toast.LENGTH_LONG)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            ContextCompat.startActivity(context, intent, null)
        }
        return response
    }
}
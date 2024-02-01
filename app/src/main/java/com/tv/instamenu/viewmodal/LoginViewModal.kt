package com.tv.instamenu.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tv.instamenu.utils.Constant
import com.tv.instamenu.utils.Constant.EMAIL
import com.tv.instamenu.utils.Constant.PASSWORD
import com.hardik.newsapp.utils.ApiException
import com.hardik.newsapp.utils.NoInternetException
import com.tv.instamenu.data.modal.BaseModal
import com.tv.instamenu.data.modal.LoginModal
import com.tv.instamenu.data.repository.LoginRepository
import com.tv.instamenu.utils.RetrofitRequestBody

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModal@Inject constructor(
    private val loginRepository: LoginRepository,
) : ViewModel() {


    private val _loginEvent = MutableSharedFlow<LoginEvent>()

    val loginEvent = _loginEvent.asSharedFlow()

    fun login(
        email:String, password:String
    ) {

        var result = ""
        try {
            val jsonBody = JSONObject()
            jsonBody.put(EMAIL, email)
            jsonBody.put(PASSWORD, password)


            result = RetrofitRequestBody.setParentJsonData(
                Constant.LOGIN,
                jsonBody
            )
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val body = RetrofitRequestBody.wrapParams(result)
        viewModelScope.launch {
            try {
                _loginEvent.emit(LoginEvent.OnSuccess(loginRepository.login(body)))

            } catch (e: ApiException) {
                e.message?.let {
                    _loginEvent.emit(LoginEvent.OnError(it))
                }
            } catch (e: NoInternetException) {
                e.message?.let {
                    _loginEvent.emit(LoginEvent.OnError(it))
                }
            } catch (e: Exception) {
                e.message?.let {
                    _loginEvent.emit(LoginEvent.OnError(it))
                }
            }
        }
    }

    sealed class LoginEvent {
        data class OnError(val error: String) : LoginEvent()
        data class OnSuccess(val response: LoginModal) : LoginEvent()
    }
}
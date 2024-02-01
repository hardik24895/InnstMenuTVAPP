package com.tv.instamenu.data.repository


import com.tv.instamenu.data.api.NetworkService
import com.tv.instamenu.data.api.SafeApiRequest
import com.tv.instamenu.data.modal.LoginModal
import okhttp3.RequestBody
import retrofit2.http.Body
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: NetworkService
) : SafeApiRequest() {


    suspend fun login(@Body body: RequestBody): LoginModal {
        return apiRequest {
            apiService.login(body)
        }
    }

    suspend fun logout(@Body body: RequestBody): LoginModal {
        return apiRequest {
            apiService.logout(body)
        }
    }

}
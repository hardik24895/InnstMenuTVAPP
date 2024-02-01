package com.tv.instamenu.data.repository



import com.tv.instamenu.data.api.NetworkService
import com.tv.instamenu.data.api.SafeApiRequest
import com.tv.instamenu.data.modal.LoginModal
import com.tv.instamenu.data.modal.ScreenModal
import okhttp3.RequestBody
import retrofit2.http.Body
import javax.inject.Inject

class ScreenRepository @Inject constructor(
    private val apiService: NetworkService
) : SafeApiRequest() {


    suspend fun screen(@Body body: RequestBody): ScreenModal {
        return apiRequest {
            apiService.screens(body)
        }
    }

}
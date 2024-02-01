package com.tv.instamenu.data.api

import com.tv.instamenu.data.modal.LoginModal
import com.tv.instamenu.data.modal.MediaListModal
import com.tv.instamenu.data.modal.ScreenModal
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @POST("login")
    suspend fun login(@Body body: RequestBody): Response<LoginModal>

    @POST("screens")
    suspend fun screens(@Body body: RequestBody): Response<ScreenModal>

    @POST("menuListapi")
    suspend fun menuList(@Body body: RequestBody): Response<MediaListModal>

    @POST("logout")
    suspend fun logout(@Body body: RequestBody): Response<LoginModal>

}
package com.tv.instamenu.di.module

import android.content.Context
import com.tv.instamenu.data.api.NetworkService

import com.hardik.newsapp.utils.AppConstant
import com.tv.instamenu.data.api.NetworkConnectionInterceptor
import com.tv.instamenu.data.api.SessionInterceptor
import com.tv.instamenu.di.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() = "https://instamenuapp.com/api/"


    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesNetworkConnectionInterceptor(
        @ApplicationContext context: Context
    ): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        sessionInterceptor: SessionInterceptor,
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(AppConstant.CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(AppConstant.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(networkConnectionInterceptor)
            .addInterceptor(sessionInterceptor)
            .addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetwork(
        @BaseUrl url: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory

    ): NetworkService {

        return Retrofit
            .Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)

    }


}
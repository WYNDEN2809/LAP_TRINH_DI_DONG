package com.example.hoc_api.network

import com.example.hoc_api.model.Product
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("v2/product")
    suspend fun getProduct(): Product
}

object RetrofitInstance {
    private const val BASE_URL = "https://mock.apidog.com/m1/890655-872447-default/"

    // Cấu hình OkHttpClient tăng thời gian chờ để tránh lỗi SocketTimeoutException
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // Thời gian chờ kết nối
            .readTimeout(30, TimeUnit.SECONDS)    // Thời gian chờ đọc dữ liệu
            .writeTimeout(30, TimeUnit.SECONDS)   // Thời gian chờ ghi dữ liệu
            .retryOnConnectionFailure(true)       // Tự động thử lại khi lỗi kết nối
            .build()
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

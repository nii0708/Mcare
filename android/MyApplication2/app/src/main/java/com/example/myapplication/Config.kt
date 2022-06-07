package com.example.myapplication

import android.bluetooth.BluetoothClass
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Config {
    val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor).connectTimeout(20, TimeUnit.MINUTES)
        .writeTimeout(20, TimeUnit.MINUTES)
        .readTimeout(20, TimeUnit.MINUTES)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://mcare4.et.r.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    fun<T> getApiService(service: Class<T>): T{
        return retrofit.create(service)
        }
}

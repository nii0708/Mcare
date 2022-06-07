package com.example.myapplication

import com.google.firebase.auth.UserInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SendingData {
    @POST("detect")
    fun PostData(@Body file: Texts): Call<predict>
}
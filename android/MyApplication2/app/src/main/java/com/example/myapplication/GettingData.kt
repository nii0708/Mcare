package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface GettingData {
    @GET("/get")
    fun GetData(): Call<List<predict>>
} //nggak kepakai di api-nya
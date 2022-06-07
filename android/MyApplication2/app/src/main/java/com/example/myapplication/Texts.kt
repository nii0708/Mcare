package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Texts ( //ganti jadi sesuai input
    @SerializedName("text1") val t1: String?,
    @SerializedName("text2") val t2: String?,
    @SerializedName("text3") val t3: String?,
) {}
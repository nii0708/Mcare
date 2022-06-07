package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class predict (
    @SerializedName("predict") val name: Int?,
) {}
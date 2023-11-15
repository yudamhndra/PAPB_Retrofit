package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET ("character")
    fun getItem(): Call<Response>
}
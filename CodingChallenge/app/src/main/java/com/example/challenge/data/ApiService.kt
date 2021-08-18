package com.example.challenge.data

import com.example.challenge.model.ResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/test/home")
    suspend fun getUsers(): Response<ResponseItem>
}
package com.example.challenge.data.helper

import com.example.challenge.model.ResponseItem
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<ResponseItem>
}
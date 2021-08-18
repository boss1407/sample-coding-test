package com.example.challenge.data.helper

import com.example.challenge.data.ApiService
import com.example.challenge.model.ResponseItem
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<ResponseItem> = apiService.getUsers()

}
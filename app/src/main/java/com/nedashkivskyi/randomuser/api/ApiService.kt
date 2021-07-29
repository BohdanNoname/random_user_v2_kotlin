package com.nedashkivskyi.randomuser.api

import com.nedashkivskyi.randomuser.pojo.PersonResponse
import com.nedashkivskyi.randomuser.pojo.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getPerson(@Url baseUrl: String): Response<PersonResponse>
}
package com.nedashkivskyi.randomuser.repository.apiRepo

import com.nedashkivskyi.randomuser.pojo.PersonResponse
import retrofit2.Response

interface ApiRepository  {
    suspend fun getPerson(): Response<PersonResponse>
}
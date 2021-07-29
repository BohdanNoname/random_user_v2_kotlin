package com.nedashkivskyi.randomuser.repository.apiRepo

import com.nedashkivskyi.randomuser.api.ApiService
import com.nedashkivskyi.randomuser.pojo.PersonResponse
import com.nedashkivskyi.randomuser.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService)
    : ApiRepository {

    override suspend fun getPerson(): Response<PersonResponse> = apiService.getPerson(Constants.BASE_URL)
}
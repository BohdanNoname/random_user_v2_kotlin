package com.nedashkivskyi.randomuser.repository.dbRepo

import androidx.lifecycle.LiveData
import com.nedashkivskyi.randomuser.pojo.Result

interface DbRepository {
    fun getAll(): LiveData<List<Result>>
    suspend fun getById(personId: IntArray): List<Result>
    suspend fun deleteAll()
    suspend fun insert(person: Result?)
    suspend fun update(person: Result)
    suspend fun delete(person: Result)
}
package com.nedashkivskyi.randomuser.repository.dbRepo

import androidx.lifecycle.LiveData
import com.nedashkivskyi.randomuser.db.PersonDao
import com.nedashkivskyi.randomuser.pojo.Result
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(
    private val dbDao: PersonDao
): DbRepository {

    override fun getAll(): LiveData<List<Result>> = dbDao.getAll()

    override suspend fun getById(personId: IntArray): List<Result> = dbDao.getById(personId)

    override suspend fun deleteAll() = dbDao.deleteAll()

    override suspend fun insert(person: Result?) = dbDao.insert(person)

    override suspend fun update(person: Result) = dbDao.update(person)

    override suspend fun delete(person: Result) = dbDao.delete(person)
}
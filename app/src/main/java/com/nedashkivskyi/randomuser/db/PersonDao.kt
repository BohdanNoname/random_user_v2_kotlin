package com.nedashkivskyi.randomuser.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.utils.Constants

@Dao
interface PersonDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun getAll(): LiveData<List<Result>>

    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE _id IN (:personId)")
    fun getById(personId: IntArray): List<Result>

    @Query("DELETE FROM ${Constants.TABLE_NAME}")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Result?)

    @Update
    fun update(person: Result)

    @Delete
    fun delete(person: Result)
}
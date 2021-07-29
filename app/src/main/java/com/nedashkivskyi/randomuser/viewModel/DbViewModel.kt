package com.nedashkivskyi.randomuser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.repository.dbRepo.DbRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DbViewModel @Inject constructor(
    private val repository: DbRepositoryImpl
) : ViewModel(){

//    private val _allPeople: MutableLiveData<List<Result>> = MutableLiveData()
    val allPeople: LiveData<List<Result>> = repository.getAll()

    private val _peopleListById: MutableLiveData<List<Result>> = MutableLiveData()
        val peopleListById: LiveData<List<Result>> = _peopleListById

    fun insert(person: Result?) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(person)
    }

    fun update(person: Result) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(person)
    }

    fun delete(person: Result) = viewModelScope.launch(Dispatchers.IO) {
            repository.delete(person)
        }

    fun getPersonById(personId: IntArray) = viewModelScope.launch(Dispatchers.IO) {
            _peopleListById.postValue(repository.getById(personId))
        }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
}
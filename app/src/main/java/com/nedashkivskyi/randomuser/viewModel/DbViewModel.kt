package com.nedashkivskyi.randomuser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.repository.dbRepo.DbRepositoryImpl
import com.nedashkivskyi.randomuser.utils.sorting.SortedPeopleList
import com.nedashkivskyi.randomuser.utils.sorting.SortedType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DbViewModel @Inject constructor(
    private val repository: DbRepositoryImpl
) : ViewModel(){

    private var _allPeople: MutableLiveData<List<Result>> = MutableLiveData()
        val allPeople: LiveData<List<Result>> = repository.getAll()

//    init {
//        _allPeople = repository.getAll() as MutableLiveData<List<Result>>
//    }

    private val _peopleListById: MutableLiveData<List<Result>> = MutableLiveData()
        val peopleListById: LiveData<List<Result>> = _peopleListById

    fun insert(person: Result?) = viewModelScope.launch(Dispatchers.IO) {
        val lastPerson = allPeople.value?.size!! - 1
        if (allPeople.value?.size == 0 || person?.equals(allPeople.value?.get(lastPerson)) != true){
            repository.insert(person)
        }
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

    fun sortedList(type: SortedType?): List<Result> =
        SortedPeopleList.sortedPeopleList(allPeople.value!!, type!!)
}
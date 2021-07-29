package com.nedashkivskyi.randomuser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedashkivskyi.randomuser.pojo.PersonResponse
import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.repository.apiRepo.ApiRepository
import com.nedashkivskyi.randomuser.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    private val _res: MutableLiveData<State<PersonResponse>> = MutableLiveData()
    val res: LiveData<State<PersonResponse>> = _res

    fun createQueryForNewPerson() = viewModelScope.launch(Dispatchers.IO) {
        _res.postValue(State.loading(null))
        apiRepository.getPerson().let {
            if (it.isSuccessful){
                _res.postValue(State.success(it.body()))
            } else{
                _res.postValue(State.error(it.body(), it.errorBody().toString()))
            }
        }
    }
}
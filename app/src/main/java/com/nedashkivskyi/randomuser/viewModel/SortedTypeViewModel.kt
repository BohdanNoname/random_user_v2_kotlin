package com.nedashkivskyi.randomuser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nedashkivskyi.randomuser.utils.sorting.SortedType
import javax.inject.Inject

class SortedTypeViewModel @Inject constructor(): ViewModel() {

    private val _sortedType: MutableLiveData<SortedType> = MutableLiveData()
    val sortedType: LiveData<SortedType> = _sortedType

    init {
        initSortedStatus()
    }

    private fun initSortedStatus(){
        _sortedType.postValue(SortedType.FROM_NEW_TO_OLD)
    }

    fun changeSortedType(type: SortedType?){
        _sortedType.postValue(type)
    }
}
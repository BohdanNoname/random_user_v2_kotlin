package com.nedashkivskyi.randomuser.utils.sorting

import com.nedashkivskyi.randomuser.pojo.Result
import com.nedashkivskyi.randomuser.utils.sorting.SortedType.*

class SortedPeopleList(
//    var peopleList: List<Result>,
//    var type: SortedType
) {
    companion object{
        fun sortedPeopleList(data: List<Result>, type: SortedType,): List<Result>{
            return when(type){
                FROM_OLD_TO_NEW ->
                    data
                FROM_NEW_TO_OLD ->
                    data.reversed()
                BY_AGE_FROM_YOUNG_TO_OLD ->
                    data.sortedBy { it.dob.age }
                BY_AGE_FROM_OLD_TO_YOUNG ->
                    data.sortedByDescending { it.dob.age }
                BY_SURNAME_A_TO_Z ->
                    data.sortedBy { it.name.last }
                BY_SURNAME_Z_TO_A ->
                    data.sortedByDescending { it.name.last }
            }
        }
//        fun <T> sortedByOldToNew(data: List<Result>): List<Result> = data
//
//        fun <T> sortedByNewToOld(data: List<Result>): List<Result> = data.reversed()
//
//        fun sortedByAgeFromYongToOld(data: List<Result>): List<Result> =
//            data.sortedBy { it.dob.age }
//
//        fun sortedByAgeFromOldToYong(data: List<Result>): List<Result> =
//            data.sortedByDescending { it.dob.age }
//
//        fun <T> sortedBySurname(data: List<Result>): List<Result> =
//            data.sortedBy { it.name.last }
    }
}
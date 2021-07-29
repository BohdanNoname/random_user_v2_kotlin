package com.nedashkivskyi.randomuser.utils

import com.nedashkivskyi.randomuser.pojo.Result

data class SortedPeopleList<out T>(
    val peopleList: List<Result>
) {

    companion object{
        fun <T> sortedByOldToNew(data: List<Result>): List<Result> = data

        fun <T> sortedByNewToOld(data: List<Result>): List<Result> = data.reversed()

        fun <T> sortedByAgeFromYongToOld(data: List<Result>): List<Result> =
            data.sortedBy { it.dob.age }

        fun sortedByAgeFromOldToYong(data: List<Result>): List<Result> =
            data.sortedByDescending { it.dob.age }

        fun <T> sortedBySurname(data: List<Result>): List<Result> =
            data.sortedBy { it.name.last }
    }
}
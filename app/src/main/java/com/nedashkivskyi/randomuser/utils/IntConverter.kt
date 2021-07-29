package com.nedashkivskyi.randomuser.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class IntConverter {

    @TypeConverter
    fun toInt(str: String?): Int? {
        return str?.toInt()
    }

    @TypeConverter
    fun toString(number: Int): String? {
        return number.toString()
    }
}
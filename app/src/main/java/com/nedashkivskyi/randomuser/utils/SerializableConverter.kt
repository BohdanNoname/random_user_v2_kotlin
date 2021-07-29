package com.nedashkivskyi.randomuser.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

abstract class SerializableConverter<out T> {
    companion object{
        inline fun <reified T> encodeToString(data: T?): String =
            Json.encodeToString(data)

        inline fun <reified T> decodeFromString(data: String?): T =
            Json.decodeFromString(data!!)
    }
}
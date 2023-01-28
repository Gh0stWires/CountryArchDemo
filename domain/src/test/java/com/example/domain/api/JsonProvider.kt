package com.example.domain.api

import com.google.common.reflect.TypeToken
import com.google.gson.Gson

object JsonProvider {
    inline fun <reified U> objectFromJsonFileWithType(filePath: String): U =
        Gson().fromJson(fileAsString(filePath), object : TypeToken<U>() {}.type)

    fun fileAsString(filePath: String): String = this::class.java
        .getResourceAsStream(filePath)!!
        .bufferedReader()
        .use { it.readText() }
}
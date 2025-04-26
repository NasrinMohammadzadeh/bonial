package com.example.bonialchallenge

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.File

inline fun <reified T> readJsonFile(filePath: String): T {
    val file = File(filePath)
    val json = file.readText()
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson(json, type)
}
package com.example.androiddevchallenge.ui

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class DogsPresenter @Inject constructor(@ApplicationContext var context: Context) {

    fun getDogs(): List<Dog> {
        val content = context.assets.open("dogs.json").bufferedReader().use {
            it.readText()
        }
        val typeToken: TypeToken<List<Dog>> = object : TypeToken<List<Dog>>() {}
        return Gson().fromJson(content, typeToken.type)
    }

}
package com.example.primetime.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.androiddata.utilities.FileHelper
import com.example.primetime.R
import com.example.primetime.model.Movies
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MoviesRepository(private  val app: Application, private val moshi:Moshi) {
    val moviesData = MutableLiveData<Movies>()

    init {
        getMoviesData()
    }

    private fun getMoviesData() {
        val json = FileHelper.getTextFromResources(app, R.raw.contents1)

        val listType = Types.newParameterizedType(Movies::class.java, Movies::class.java)
        val adapter: JsonAdapter<Movies> = moshi.adapter(listType)
        moviesData.value = adapter.fromJson(json)

    }


}
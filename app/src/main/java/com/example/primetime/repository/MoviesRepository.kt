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
        getMoviesData(R.raw.contents1)
    }

     private fun getMoviesData(resourceId:Int) {
        val json = FileHelper.getTextFromResources(app,resourceId)

        val listType = Types.newParameterizedType(Movies::class.java, Movies::class.java)
        val adapter: JsonAdapter<Movies> = moshi.adapter(listType)
        moviesData.value = adapter.fromJson(json)

    }


}
package com.example.primetime.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.primetime.model.Content
import com.example.primetime.repository.MoviesRepository

class MoviesViewmodel(private val repo :MoviesRepository ) :ViewModel(){
val moviesData =repo.moviesData


}
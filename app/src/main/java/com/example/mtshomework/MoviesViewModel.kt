package com.example.mtshomework

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel: ViewModel() {

    val movies: LiveData<List<MovieDto>> get() = _movies
    private val _movies = MutableLiveData<List<MovieDto>>()


    private fun prepareMovies(): List<MovieDto> {
        return MoviesDataSourceImpl().getMovies()
    }

    fun updateMovies() {
        SystemClock.sleep(2000)
        val movies = prepareMovies().shuffled()
        _movies.postValue(movies)
    }
}

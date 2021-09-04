package com.example.mtshomework

import android.content.Context
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel: ViewModel() {

    val movies: LiveData<List<Movie>> get() = _movies
    private val _movies = MutableLiveData<List<Movie>>()

    private var database: AppDatabase? = null

    fun initDatabase(context: Context){
        database = AppDatabase.getInstance(context)
        if (database?.movieDao()?.getAll()?.size == 0)
            database?.movieDao()?.insertAll(MoviesDataSourceImpl().getMovies())
    }


    fun prepareMovies() {
        _movies.postValue(database?.movieDao()?.getAll())
    }

    fun updateMovies() {
        SystemClock.sleep(2000)
        _movies.postValue(database?.movieDao()?.getAll()?.shuffled())
    }
}

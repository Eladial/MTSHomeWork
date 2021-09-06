package com.example.mtshomework

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel: ViewModel() {

    val movies: LiveData<List<MovieResponse>> get() = _movies
    private val _movies = MutableLiveData<List<MovieResponse>>()

    private var database: AppDatabase? = null


    fun initDatabase(context: Context){
        var moviesResponse: List<MovieResponse>
        database = AppDatabase.getInstance(context)
        App.instance.apiService.getPopular().enqueue(object : Callback<PopularResponse> {
            override fun onResponse(
                call: Call<PopularResponse>,
                response: Response<PopularResponse>
            ) {
                moviesResponse = response.body()?.results ?: emptyList()
                Log.i("response", "List:")
                for (i in moviesResponse)
                    Log.i("response", "$i")
                database?.movieDao()?.insertAll(moviesResponse)

            }

            override fun onFailure(call: Call<PopularResponse>, t: Throwable) {
                Log.i("retrofit", "$t")
            }
        })


    }


    fun prepareMovies() {
        _movies.postValue(database?.movieDao()?.getAll())
    }

    fun updateMovies() {
        SystemClock.sleep(2000)
        _movies.postValue(database?.movieDao()?.getAll()?.shuffled())
    }
}
